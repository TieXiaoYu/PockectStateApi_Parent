package com.pockectstate_api.pockectstate_appapi.service;

import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-13 16:11
 */
@FeignClient(name = "LoginProvider")
public interface LoginService {
    //登录
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto);
    //注销
    @GetMapping("auth/loginout.do")
    public R loginout();
    //检查
    @GetMapping("auth/checktoken.do")
    public R check();
    //找回密码
    @PostMapping("auth/getbackpassword.do")
    public R goback(@RequestBody UserDto userDto);
}
