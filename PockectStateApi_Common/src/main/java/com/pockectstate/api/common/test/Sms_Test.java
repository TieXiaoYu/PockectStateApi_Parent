package com.pockectstate.api.common.test;

import com.pockectstate.api.common.util.JuHeSms_Util;
import com.pockectstate.api.common.util.Random_Util;
import org.junit.Test;

import java.io.UnsupportedEncodingException;


public class Sms_Test {
    @Test
    public void t1() throws UnsupportedEncodingException {
        int code=Random_Util.createNum(6);
        System.out.println(JuHeSms_Util.sendMsg("13752704557",code));
        System.out.println(code);
    }
}
