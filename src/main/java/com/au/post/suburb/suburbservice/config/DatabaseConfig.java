package com.au.post.suburb.suburbservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author bnaragani created on 14/08/2021
 */
@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfig {

}
