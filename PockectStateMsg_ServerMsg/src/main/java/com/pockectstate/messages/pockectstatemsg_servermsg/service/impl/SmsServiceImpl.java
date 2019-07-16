package com.pockectstate.messages.pockectstatemsg_servermsg.service.impl;

import com.pockectstate.api.common.config.RedisKey_config;
import com.pockectstate.api.common.psenum.MsgLogType;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.common.util.JuHeSms_Util;
import com.pockectstate.api.common.util.Random_Util;
import com.pockectstate.api.common.util.TimeUtil;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.msg.MsgLog;
import com.pockectstate.entity.msg.SmsRestset;
import com.pockectstate.entity.msg.SmsSend;
import com.pockectstate.messages.pockectstatemsg_servermsg.dao.MsgLogMapper;
import com.pockectstate.messages.pockectstatemsg_servermsg.dao.SmsRestsetMapper;
import com.pockectstate.messages.pockectstatemsg_servermsg.dao.SmsSendMapper;
import com.pockectstate.messages.pockectstatemsg_servermsg.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.UnsupportedEncodingException;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 20:05
 */
@Service
public class SmsServiceImpl implements SmsService {
    @Autowired(required = false)
    private SmsSendMapper smsSendMapper;
    @Autowired(required = false)
    private MsgLogMapper msgLogMapper;
    @Autowired
    private JedisUtil jedisUtil;
    @Autowired(required = false)
    private SmsRestsetMapper smsRestsetMapper;
    @Override
    public R save(SmsSend smsSend) {
        smsSendMapper.insert(smsSend);
        MsgLog log = new MsgLog();
        log.setType(MsgLogType.volidatecode.getIndex());
        log.setContent("发送:"+smsSend.getPhone()+"验证码");
        msgLogMapper.insert(log);
        return R.setOK("OK",null);
    }

    @Override
    public R checkCode(String phone, int code) {
        if(jedisUtil.exists(RedisKey_config.VCODE_CODE+phone)){
            //校验是否正确
            String c = jedisUtil.get(RedisKey_config.VCODE_CODE+phone);
            if(code == Integer.parseInt(c)){
                //成功
                return R.setOK("OK",null);
            }else {
                return R.setERROR("验证码不一致",null);
            }
        }else {
            return R.setERROR("验证码已过期,请重新获取",null);
        }
    }

    @Override
    @Transactional
    public R sendSms(String phone,String ip) {
        boolean issend = true;//记录是否可以发送短信
        String msg = "";//记录返回的内容
        //验证手机号是否可发
        if(jedisUtil.exists(RedisKey_config.VCODE_FIRST+phone)){
            //一分钟内发过
            msg="一分钟内发送过,请查看手机短信";
            issend=false;
        }else if(jedisUtil.exists(RedisKey_config.VCODE_SECOND+phone)){
            //10分钟 3次
            int c = Integer.parseInt(jedisUtil.get(RedisKey_config.VCODE_SECOND+phone));
            if(c>=3){
                issend=false;
                msg="10分钟内已经达到上限";
            }
        }else if(jedisUtil.exists(RedisKey_config.VCODE_THREE+phone)){
            //一小时4次
            int c = Integer.parseInt(jedisUtil.get(RedisKey_config.VCODE_THREE+phone));
            if(c>=4){
                issend=false;
                msg="1小时内已经达到上限";
            }
        }else  if(jedisUtil.exists(RedisKey_config.VCODE_FOUR+phone)){
            //一天20次
            int c = Integer.parseInt(jedisUtil.get(RedisKey_config.VCODE_FOUR+phone));
            if(c>=20){
                issend=false;
                msg="今天验证码次数已经达到上限";
            }
        }
        boolean isfirst=true;
        int f =1;
        if(issend){
            //说明该手机号可以发送短信
            int cd= 0;

            if(jedisUtil.exists(RedisKey_config.VCODE_CODE+phone)){
                //如果发过  就取出重新发送
                cd= Integer.parseInt(jedisUtil.get(RedisKey_config.VCODE_CODE+phone));
                isfirst=false;
            }else {
                cd= Random_Util.createNum(6);
            }

            try {
                //发送短信
                JuHeSms_Util.sendMsg(phone,cd);
                //如果发送成功  更新次数 4个
                //1分钟
                if(isfirst){
                    //验证码 5分钟有效
                    jedisUtil.setExpire(RedisKey_config.VCODE_CODE+phone,cd+"",300);

                }
                //设置1分钟
                setValue(RedisKey_config.VCODE_FIRST+phone,60);
                //设置10分钟
                setValue(RedisKey_config.VCODE_SECOND+phone,600);
                //设置一小时
                setValue(RedisKey_config.VCODE_THREE+phone,3600);
                //设置一天
                setValue(RedisKey_config.VCODE_FOUR+phone, TimeUtil.getLastSeconds());
                msg=phone+"验证码:"+cd;

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                msg=phone+"验证码:"+cd;
                f=2;
            }
        }
        //数据库记录
        SmsSend smsSend1 = smsSendMapper.selectByMsg(phone,msg);
        if(smsSend1 == null){
            //第一次发送
            SmsSend smsSend = new SmsSend();
            smsSend.setPhone(phone);
            smsSend.setContent(msg);
            smsSend.setFlag(f);
            smsSend.setIpaddr(ip);
            smsSendMapper.insert(smsSend);
        }else {
            //这个验证码不是第一次发送
                SmsRestset smsRestset = new SmsRestset();
                smsRestset.setFlag(f);
                smsRestset.setSid(smsSend1.getId());
                smsRestsetMapper.insert(smsRestset);
        }
        MsgLog log = new MsgLog();
        log.setContent(msg);
        log.setType(MsgLogType.volidatecode.getIndex());
        msgLogMapper.insert(log);
        return R.setR(f==1,"验证码发送",null);
    }
    private void setValue(String key,int seconds){
        if(jedisUtil.exists(key)){
            jedisUtil.setExpire(key,Integer.parseInt(jedisUtil.get(key))+1+"",(int)jedisUtil.ttl(key));
        }else {
            //第一次 设置有效期
            jedisUtil.setExpire(key,1+"",seconds);
        }
    }
}
