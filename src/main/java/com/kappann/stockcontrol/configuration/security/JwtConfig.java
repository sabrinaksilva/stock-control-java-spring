package com.kappann.stockcontrol.configuration.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtConfig {
    @Value("${api.security.token.secret}")
    private String jwtSecret;

    @Value("${api.security.token.issuer}")
    private String issuer;


}
