package com.aleksa1996.ticketingsystem.infrastructure.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;

@Service
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String issueToken(
            String subject,
            Map<String, Object> claims,
            long durationInMinutes) {

        String token = Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuer("https://ticketing-system.com")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(durationInMinutes, ChronoUnit.MINUTES)))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();

        return token;
    }

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String getSubject(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    private Claims getClaims(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }

    public boolean isTokenValid(String jwt, String username) {
        String subject = getSubject(jwt);
        return subject.equals(username) && !isTokenExpired(jwt);
    }

    private boolean isTokenExpired(String jwt) {
        return getClaims(jwt).getExpiration().before(Date.from(Instant.now()));
    }
}