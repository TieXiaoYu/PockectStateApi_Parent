package com.pockectstate.entity.user;

import lombok.Data;


@Data
public class User {
    private Integer id;
    private String phone;
    private String password;
    private Integer flag; //1、有效 2无效

}
