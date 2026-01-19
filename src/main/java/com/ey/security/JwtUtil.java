package com.ey.security;

import java.nio.charset.StandardCharsets;

import java.security.Key;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.*;

import io.jsonwebtoken.security.Keys;

@Component

public class JwtUtil {

    private static final String SECRET = "home_cleaning_management_secret_key_2026_secure";

    private static final long EXPIRATION = 86400000; // 1 day

    private Key getKey() {

        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    }

    public String generateToken(String email, String role) {

        return Jwts.builder()

                .setSubject(email)

                .claim("role", role)

                .setIssuedAt(new Date())

                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))

                .signWith(getKey(), SignatureAlgorithm.HS256)

                .compact();

    }

    public String extractEmail(String token) {

        return parseClaims(token).getSubject();

    }

    public String extractRole(String token) {

        return parseClaims(token).get("role", String.class);

    }

    private Claims parseClaims(String token) {

        return Jwts.parserBuilder()

                .setSigningKey(getKey())

                .build()

                .parseClaimsJws(token)

                .getBody();

    }

}
 