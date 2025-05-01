package com.practice.fsa.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

public class JWTUtil {

    private static final String algorithm = "HmacSHA512";
    private static SecretKey secretKey = null;

    static {
        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        keyGen.init(512);
        secretKey = keyGen.generateKey();
    }

    public static String generateToken(String username) {
        return Jwts.builder()
                .claims()
                .add(new HashMap<String,Object>())
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() +  60 * 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
    }

    private static Key getKey() {
        return Keys.hmacShaKeyFor(secretKey.getEncoded());
    }

    public static String extractUsername(String token) {
        return JWTUtil.extractAllClaims(token).getSubject();
    }

    private static Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build().
                parseSignedClaims(token)
                .getPayload();
    }

    private static boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    public static boolean validateToken(String token, UserDetails userDetails) {
        final String username = JWTUtil.extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
