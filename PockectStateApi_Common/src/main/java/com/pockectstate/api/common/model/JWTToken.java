package com.pockectstate.api.common.model;

import lombok.Data;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 19:43
 * JWT实现登录授权令牌
 * jwt中的有效负载  content
 */
@Data
public class JWTToken {
    private int id;//用户id
    private String phone;//手机号
    private String no;//jwt令牌的序号
    private int device;//设备
    private String deviceMac;//设备的mac 唯一地址
}
