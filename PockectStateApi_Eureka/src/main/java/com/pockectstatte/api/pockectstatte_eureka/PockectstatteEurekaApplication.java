package com.pockectstatte.api.pockectstatte_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PockectstatteEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PockectstatteEurekaApplication.class, args);
    }

}
