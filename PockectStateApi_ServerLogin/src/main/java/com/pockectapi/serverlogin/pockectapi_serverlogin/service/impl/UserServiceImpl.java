package com.pockectapi.serverlogin.pockectapi_serverlogin.service.impl;

import com.alibaba.fastjson.JSON;
import com.pockectapi.serverlogin.pockectapi_serverlogin.dao.UserDao;
import com.pockectapi.serverlogin.pockectapi_serverlogin.service.UserService;
import com.pockectapi.serverlogin.pockectapi_serverlogin.until.DeviceKey_Util;
import com.pockectstate.api.common.config.JWT_Config;
import com.pockectstate.api.common.config.Key_Config;
import com.pockectstate.api.common.config.RedisKey_config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.model.JWTToken;
import com.pockectstate.api.common.util.EncryptionUtil;
import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.util.JWT_Util;
import com.pockectstate.api.common.util.JedisUtil;
import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 19:32
 * 多设备唯一登录  相同设备只能在线一个
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    private UserDao userDao;
    @Autowired
    private IdGenerator idGenerator;

    private JedisUtil jedisUtil = JedisUtil.getInstance();
    @Override
    public R login(LoginDto loginDto) {

        //验证账号是否冻结
        if(jedisUtil.exists(RedisKey_config.LOGINFORCE+loginDto.getPhone())){
            return R.setERROR("该账号已冻结,剩余时间:"+getTTL(RedisKey_config.LOGINFORCE+loginDto.getPhone()),null);
        }else {
            boolean login = false;
            //验证手机号是否存在
            User user = userDao.selectByPhone(loginDto.getPhone());
            if(user != null){
                //验证密码是否正确
                if(Objects.equals(user.getPassword(), EncryptionUtil.AESEnc(Key_Config.PASSKEY,loginDto.getPassword()))){
                    //登录成功之后
                    //生成令牌
                    JWTToken jwtToken = new JWTToken();
                    jwtToken.setDevice(loginDto.getDevice());
                    jwtToken.setDeviceMac(loginDto.getDeviceMac());
                    jwtToken.setPhone(loginDto.getPhone());
                    jwtToken.setId(user.getId());
                    jwtToken.setNo(idGenerator.nextId()+"");
                    String jsonToken = JSON.toJSONString(jwtToken);
                    String token = JWT_Util.createJWT(idGenerator.nextId()+"", JWT_Config.JWTTOKENTIME,jsonToken);
                    //存储到redis
                    //当前的令牌 值为对应的jwttoken的JSON对象
                    jedisUtil.setExpire(RedisKey_config.JWTTOKEN_TOKEN+token,jsonToken,JWT_Config.JWTTOKENTIME*60);
                    //当前的设备和账号信息  值为对应的令牌
                    jedisUtil.setExpire(RedisKey_config.JWTTOKEN_DEVICE+DeviceKey_Util.createKey(jwtToken),token,JWT_Config.JWTTOKENTIME*60);
                    login = true;
                    return R.setOK("OK",token);
                }
            }
            if(!login){
                String k = RedisKey_config.LOGINERROR+loginDto.getPhone();
                if(jedisUtil.exists(k)){
                    int c = Integer.parseInt(jedisUtil.get(k));
                    jedisUtil.setExpire(k,c+1+"",(int) jedisUtil.ttl(k));
                    if(c>=2){
                        jedisUtil.setExpire(RedisKey_config.LOGINFORCE+loginDto.getPhone(),loginDto.getPhone(),15*60);
                        return R.setERROR("您已经连续输错三次,账号将被冻结15分钟",null);
                    }
                }else {
                    //第一次失败
                    jedisUtil.setExpire(k,1+"",300);
                }
            }
            return R.setERROR("手机号或密码错误",null);
        }

    }

    @Override
    public R loginout(String token) {
        String js = JWT_Util.parseJWT(token);
        if(js != null &&js.length()>0){
            jedisUtil.del(RedisKey_config.JWTTOKEN_TOKEN+token);
            JWTToken jwtToken = JSON.parseObject(js,JWTToken.class);
            jedisUtil.del(RedisKey_config.JWTTOKEN_DEVICE+DeviceKey_Util.createKey(jwtToken));
        }
        return R.setOK("请选择操作: 重新登录 切换账号",null);
    }

    @Override
    public R goBackPass(UserDto userDto) {
        if(!jedisUtil.exists(RedisKey_config.LOGINFORCE+userDto.getPhone())) {
            int r = userDao.updatePass(userDto.getPhone(), EncryptionUtil.AESDec(JWT_Config.JWTKEY, userDto.getPsw()));
            if (r > 0) {
                //修改成功之后 相关的key全部干掉
                Set<String> keys = jedisUtil.keys(RedisKey_config.JWTTOKEN_DEVICE + userDto.getPhone() + "_*");
                String[] arr = new String[keys.size()];
                int i = 0;
                for (String k : keys) {
                    arr[i] = k;
                    i++;
                }
                jedisUtil.del(arr);
                return R.setOK("密码找回成功,请妥善保管", null);
            }
            return R.setERROR("密码找回失败,请稍后再试", null);
        }else {
            return R.setERROR("亲,您的账号还在冻结中,等待剩余时间:"+getTTL(RedisKey_config.LOGINFORCE+userDto.getPhone()),null);
        }
    }

    /*校验令牌是否符合JWT规则*/
    @Override
    public R checkToken(String token) {
        //验证token是否有效
        //基于JWT 校验令牌是否符合JWT
        if(JWT_Util.checkJWT(token)){
            //校验成功  符合JWT格式
            if(jedisUtil.exists(RedisKey_config.JWTTOKEN_TOKEN+token)){
                //再检验手机号和设备对应的令牌和当前令牌是否一致
                String json = JWT_Util.parseJWT(token);
                JWTToken jwtToken = JSON.parseObject(json,JWTToken.class);
                //校验同种设备下是否为当前的令牌
                if(jedisUtil.exists(RedisKey_config.JWTTOKEN_TYPE+"_"+jwtToken.getPhone()+"_"+jwtToken.getDevice())){
                    String t=jedisUtil.get(RedisKey_config.JWTTOKEN_TYPE+"_"+jwtToken.getPhone()+"_"+jwtToken.getDevice());
                    if(Objects.equals(t,token)){
                        //校验相同设备下的令牌是否一致
                        String dk=DeviceKey_Util.createKey(jwtToken);
                        if(jedisUtil.exists(RedisKey_config.JWTTOKEN_DEVICE+dk)){
                            //取出 设备对应的令牌
                            String t1=jedisUtil.get(RedisKey_config.JWTTOKEN_DEVICE+dk);
                            if(Objects.equals(t1,token)){
                                return R.setOK("OK",null);
                            }else {
                                jedisUtil.del(RedisKey_config.JWTTOKEN_TOKEN+token);
                                return R.setERROR("已经在其他设备上登录，被迫下线",null);
                            }
                        }
                    }
                }
            }
        }
        return R.setERROR("登录失效,请重新登录",null);
    }
    private String getTTL(String key){
        return jedisUtil.ttl(key)/60+"分钟";
    }
}
