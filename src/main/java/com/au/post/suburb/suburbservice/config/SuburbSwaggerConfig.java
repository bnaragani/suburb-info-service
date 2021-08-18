package com.au.post.suburb.suburbservice.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bnaragani created on 14/08/2021
 */
@Configuration
@EnableSwagger2
public class SuburbSwaggerConfig {
    @Bean
    public Docket apiDocket() {

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.au.post.suburb.suburbservice"))
                .build()
                .apiInfo(metaData());

        return docket;
    }

    private ApiInfo metaData() {
        return new ApiInfo("Postal Suburb REST APIs",
                "REST APIs for Suburb Service",
                "1.0",
                "Terms of service",
                new Contact("Bhavani Naragani", " ", "bhavaninaragani@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
