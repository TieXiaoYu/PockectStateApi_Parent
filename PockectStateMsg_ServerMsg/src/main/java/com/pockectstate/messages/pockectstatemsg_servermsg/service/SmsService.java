package com.pockectstate.messages.pockectstatemsg_servermsg.service;

import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.msg.SmsSend;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 20:03
 */
public interface SmsService {

    R save(SmsSend smsSend);

    R checkCode(String phone,int code);

    R sendSms(String phone,String ip);
}
