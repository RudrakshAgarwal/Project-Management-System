package com.projectManagement.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private final JwtProperties jwtProperties;

    public JwtProvider(JwtProperties jwtProperties) {
        this.jwtProperties = jwtProperties;
    }

    public String generateToken(Authentication auth) {
        return Jwts.builder().issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 864000000))
                .claim("email", auth.getName())
                .signWith(jwtProperties.getKey())
                .compact();
    }

    public String getEmailFromToken(String jwt) throws Exception {
        if (jwt != null && jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        } else
            throw new Exception("Invalid Auth Type Header...");

        Claims claims = Jwts.parser().verifyWith(jwtProperties.getKey()).build().parseSignedClaims(jwt).getPayload();
        return String.valueOf(claims.get("email"));
    }
}
