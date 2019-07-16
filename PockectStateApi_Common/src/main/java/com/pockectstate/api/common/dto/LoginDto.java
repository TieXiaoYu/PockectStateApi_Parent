package com.pockectstate.api.common.dto;

import lombok.Data;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-12 15:10
 */
@Data
public class LoginDto {
    private String phone;//手机号
    private String password;//密码
    private int device;//设备类型
    private String deviceMac;//设备的Mac 唯一地址 //app和小程序获取的是mac  如果是网页记录ip
}
