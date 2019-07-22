package com.pockectstate.search.pockectstateapi_serversearch.config;

import com.pockectstate.api.common.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-22 22:41
 */
@Configuration
public class IdAutoConfig {
    @Bean
    public IdGenerator createId(){
        return new IdGenerator();
    }
}
