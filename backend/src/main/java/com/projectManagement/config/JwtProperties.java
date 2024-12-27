package com.projectManagement.config;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtProperties {
    private final String secretKey;
    private final String jwtHeader;
    private final SecretKey key;

    public JwtProperties(
            @Value("${JWT_SECRET_KEY}") String secretKey,
            @Value("${JWT_HEADER}") String jwtHeader) {
        this.secretKey = secretKey;
        this.jwtHeader = jwtHeader;
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public SecretKey getKey() {
        return key;
    }

    public String getJwtHeader() {
        return jwtHeader;
    }
}
