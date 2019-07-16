package com.pockectstate.entity.user;

import lombok.Data;

import java.math.BigInteger;
import java.util.Date;


@Data
public class UserLog {
    private BigInteger id;
    private String content;
    private Integer uid;
    private Integer type; //1、注册 2登录
    private Date ctime;
}
