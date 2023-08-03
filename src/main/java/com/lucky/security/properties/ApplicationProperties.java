package com.lucky.security.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: lenka
 * @date: 2023-05-04 3:20 PM
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Security security = new Security();

    public Security getSecurity() {
        return security;
    }

    @Data
    public static class Security {
        private long tokenValidityInSeconds;
    }
}
