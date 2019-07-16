package com.pockectstate.api.pockectstateapi_serveruser.service;

import com.pockectstate.api.common.dto.UserDto;
import com.pockectstate.api.common.vo.R;


public interface UserService {
    R save(UserDto userDto);
    R queryByPhone(String phone);
}
