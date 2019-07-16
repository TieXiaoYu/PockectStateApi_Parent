package com.pockectstate_api.pockectstate_appapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * @AUTHOR 铁小雨
 * @CREATE 2019-07-08 20:44
 */
@Configuration
public class SwaggerConfig {
    private ApiInfo createAI(){
        return  new ApiInfoBuilder().title("兜儿邦APP数据接口平台").description("基于SpringBoot+SpringCloud实现的微服务架构,提供兜儿邦的所有接口").version("1.0")
                .contact(new Contact("Txy","http://111","txy13752704557.@163.com")).build();

    }
    @Bean
    public Docket createD(){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(createAI()).select().apis
                (RequestHandlerSelectors.basePackage("com.pockectstate_api.pockectstate_appapi.api")).build();
    }

}
