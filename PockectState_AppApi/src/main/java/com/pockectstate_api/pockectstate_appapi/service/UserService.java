package com.pockectstate_api.pockectstate_appapi.service;

import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 11:35
 */
@FeignClient(name = "UserProvider")
public interface UserService {
    @GetMapping("user/checkphone.do")
     R check(@RequestParam("phone")String phone);
    @PostMapping("user/register.do")
     R save(@RequestBody UserDto userDto);
}
