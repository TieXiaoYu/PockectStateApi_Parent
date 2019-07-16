package com.pockectstate_api.pockectstate_appapi.service;

import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 21:56
 */
@FeignClient(name = "MsgProvider")
public interface SmsService {

    //发送短信
    @PostMapping("message/sendsmscode.do")
    R sendSms(@RequestParam("phone") String phone);
    //验证短信验证码
    @GetMapping("message/checksmscode.do")
    R check(@RequestParam("phone") String phone,@RequestParam("code") int code);
}
