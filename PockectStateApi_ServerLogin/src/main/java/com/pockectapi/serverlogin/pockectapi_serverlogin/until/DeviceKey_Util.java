package com.pockectapi.serverlogin.pockectapi_serverlogin.until;

import com.pockectstate.api.common.model.JWTToken;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-12 15:26
 */
public class DeviceKey_Util {
    public static String createKey(JWTToken jwtToken){
        StringBuffer buffer = new StringBuffer();
        buffer.append(jwtToken.getPhone());
        buffer.append("_");
        buffer.append(jwtToken.getDevice());
        buffer.append("_");
        buffer.append(jwtToken.getDeviceMac());
        return buffer.toString();
    }
}
