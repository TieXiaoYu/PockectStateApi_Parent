package com.pockectstate_api.pockectstate_appapi.api;

import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import com.pockectstate_api.pockectstate_appapi.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-13 16:13
 */
@Api(value = "统一鉴权中心",tags = "统一鉴权中心")
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    //登录
    @ApiOperation(value = "登录",notes = "实现登录,基于JWT")
    @PostMapping("auth/login.do")
    public R login(@RequestBody LoginDto loginDto){
        return  loginService.login(loginDto);
    }
    //注销
    @ApiOperation(value = "注销",notes = "实现注销")
    @GetMapping("auth/loginout.do")
    public R loginout(HttpServletRequest request){
        return loginService.loginout();
    }
    //检查
    @ApiOperation(value = "检查令牌",notes = "检查令牌")
    @GetMapping("auth/checktoken.do")
    public R check(HttpServletRequest request){
        return loginService.check();
    }
    //找回密码
    @ApiOperation(value = "找回密码",notes = "找回密码")
    @PostMapping("auth/getbackpassword.do")
    public R goback(@RequestBody UserDto userDto){
        return loginService.goback(userDto);
    }
}
