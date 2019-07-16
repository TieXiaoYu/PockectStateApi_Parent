package com.pockectstate.api.common.test;

import com.pockectstate.api.common.util.IdGenerator;
import com.pockectstate.api.common.util.JWT_Util;
import org.junit.Test;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-11 15:22
 */
public class JWT_Test {
    @Test
    public void t1(){
        String m = "13752704557";
        IdGenerator idGenerator = new IdGenerator();
        String pass = JWT_Util.createJWT(idGenerator.nextId()+"",30,m);
        System.out.println("JWT: "+pass);
        System.out.println("JWT解析: "+JWT_Util.parseJWT(pass));
    }
}
