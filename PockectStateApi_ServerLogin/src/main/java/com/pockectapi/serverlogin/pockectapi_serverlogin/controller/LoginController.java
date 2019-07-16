package com.pockectapi.serverlogin.pockectapi_serverlogin.controller;

import com.pockectapi.serverlogin.pockectapi_serverlogin.service.UserService;
import com.pockectstate.api.common.config.JWT_Config;
import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-13 15:55
 */
@RestController
public class LoginController {
    @Autowired
    private UserService userService;
    //登录
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto){
        return  userService.login(loginDto);
    }
    //注销
    @GetMapping("auth/loginout.do")
    public R loginout(HttpServletRequest request){
        return userService.loginout(request.getHeader(JWT_Config.HEADERTOKEN));
    }
    //检查
    @GetMapping("auth/checktoken.do")
    public R check(HttpServletRequest request){
        return userService.checkToken(request.getHeader(JWT_Config.HEADERTOKEN));
    }
    //找回密码
    @PostMapping("auth/getbackpassword.do")
    public R goback(@RequestBody UserDto userDto){
        return userService.goBackPass(userDto);
    }
}
