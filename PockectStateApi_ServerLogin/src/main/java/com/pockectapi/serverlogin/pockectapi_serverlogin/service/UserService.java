package com.pockectapi.serverlogin.pockectapi_serverlogin.service;

import com.pockectstate.api.common.dto.LoginDto;
import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 19:30
 */
public interface UserService {
    //登录
    R login(LoginDto loginDto);
    //注销
    R loginout(String token);
    //找回密码
    R goBackPass(UserDto userDto);
    //校验令牌
    R checkToken(String token);
}
