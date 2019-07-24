package com.pockectstate_api.pockectstate_appapi.service;


import com.pockectstate.api.common.vo.R;
import com.pockectstate.entity.user.TAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *@Author feri
 *@Date Created in 2019/7/24 15:19
 */
@FeignClient(name = "UserProvider")
public interface AddressService {
    @GetMapping("addrss/default.do")
    R defaultad();
    @GetMapping("address/alluid.do")
    R all();
    @PostMapping("/address/save.do")
    R save(@RequestBody TAddress address);
}
