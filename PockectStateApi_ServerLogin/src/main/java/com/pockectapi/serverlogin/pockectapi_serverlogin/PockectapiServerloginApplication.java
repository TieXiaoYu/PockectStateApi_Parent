package com.pockectapi.serverlogin.pockectapi_serverlogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.pockectapi.serverlogin.pockectapi_serverlogin.dao")
@EnableEurekaClient
public class PockectapiServerloginApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectapiServerloginApplication.class, args);
    }

}
