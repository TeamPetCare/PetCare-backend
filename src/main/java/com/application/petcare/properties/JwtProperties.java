package com.application.petcare.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "app.security.jwt")
@Getter
@Setter
@Component
public class JwtProperties {
    private String secretKey;
    private String expiration;
}