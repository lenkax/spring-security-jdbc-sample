package com.lucky.security;

import com.lucky.security.properties.ApplicationProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author: lenka
 * @date: 2023-04-30 2:44 PM
 */
@SpringBootApplication
@MapperScan("com.lucky.security.mapper")
@EnableConfigurationProperties({ApplicationProperties.class})
public class SpringSecurityJdbcApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJdbcApplication.class, args);
    }
}

