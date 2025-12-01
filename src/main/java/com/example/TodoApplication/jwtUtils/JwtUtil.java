package com.example.TodoApplication.jwtUtils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.KeyStore;
import java.util.Date;

@Component
public class JwtUtil {
    private String SECRET="abcdefghijklmnop_123456778etryhw5ruhjteu7j6tg76ij89999";
    private int EXPIRATION=1000*60*10;
    private Key SECRETKEY= Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    public String generateToken(String email,String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role",role)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(SECRETKEY, SignatureAlgorithm.HS256)
                .compact();
    }
    public String ExtractEmail(String token){
        return Jwts.parserBuilder()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String ExtractRole(String token){
        return (String) Jwts.parserBuilder()
                .setSigningKey(SECRETKEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
    public Boolean validateToken(String token){
        try{
            ExtractEmail(token);
            return true;
        }catch (Exception e){
            throw new RuntimeException("token is not valid");
        }

    }
}
