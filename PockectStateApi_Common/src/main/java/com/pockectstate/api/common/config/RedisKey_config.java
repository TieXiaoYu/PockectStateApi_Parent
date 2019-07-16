package com.pockectstate.api.common.config;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 20:42
 * 基于redis存储数据
 */
public class RedisKey_config {
    //记录手机短信验证码  5分钟
    public static final String VCODE_CODE="vc:";
    //记录一分钟内 1次
    public static final String VCODE_FIRST="vc_first:";
    //记录十分钟 3次
    public static final String VCODE_SECOND="vc_second:";
    //记录一小时 4次
    public static final String VCODE_THREE="vc_three:";
    //记录一天  20次
    public static final String VCODE_FOUR="vc_four:";
    //记录登录令牌相关的Key
    public static final String JWTTOKEN_DEVICE="jwtdevice";//手机号_设备号_设备的mac地址
    public static final String JWTTOKEN_TOKEN="jwttoken";//令牌

    //记录登录失败次数
    public static String LOGINERROR="loginerror:";//phone 失效期15分钟
    //记录冻结的账号
    public static String LOGINFORCE="loginforce:";//phone 失效期15分钟

}
