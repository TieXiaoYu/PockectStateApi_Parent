package com.pockectstate_api.pockectstate_appapi.api;

import com.pockectstate.api.common.vo.R;
import com.pockectstate_api.pockectstate_appapi.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 22:04
 */
@Api(value = "短信操作",tags = "短信操作")
@RestController
public class SmsController {
    @Autowired
    private SmsService smsService;

    @ApiOperation(value = "实现短信验证码的发送",notes = "实现短信验证码的发送")
    @PostMapping("api/message/sendsmscode.do")
    public R sendSms(@RequestParam("phone") String phone){
        return smsService.sendSms(phone);
    }
    //验证短信验证码
    @ApiOperation(value = "实现短信验证码的校验",notes = "实现短信验证码的校验")
    @GetMapping("api/message/checksmscode.do")
    public R check(@RequestParam("phone") String phone,@RequestParam("code") int code){
        return smsService.check(phone,code);
    }
}
