package com.pockectstate.messages.pockectstatemsg_servermsg.config;

import com.pockectstate.api.common.util.JedisUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-10 20:46
 */
@Configuration
public class JedisConfig {

    @Bean
    public JedisUtil createJU(){
        return JedisUtil.getInstance();
    }
}
