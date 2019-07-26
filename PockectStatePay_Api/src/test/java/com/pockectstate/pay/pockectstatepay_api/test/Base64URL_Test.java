package com.pockectstate.pay.pockectstatepay_api.test;

import com.pockectstate.api.common.util.Base64Util;
import org.junit.Test;

import java.util.Base64;

/**
 *@Author feri
 *@Date Created in 2019/7/25 16:01
 */
public class Base64URL_Test {
    @Test
    public void t1(){
        String m="http://221212.com";
        String m1=Base64.getUrlEncoder().encodeToString(m.getBytes());
        System.out.println(m1);
        System.out.println("ddd："+Base64Util.base64Enc(m.getBytes()));
        System.out.println("解密："+new String(Base64.getUrlDecoder().decode(m1)));
    }
}
