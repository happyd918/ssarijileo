package com.ssafy.ssarijileo.user.service;

import com.ssafy.ssarijileo.user.dto.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class TokenProvider implements InitializingBean {
    private final String secret;
    private final long tokenValidityInMilliseconds;     // 30 min

    private Key key;

    public TokenProvider(
        @Value("${jwt.secret}")String secret,
        @Value("${jwt.token-validity-in-seconds}")long tokenValidityInSeconds
    ) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Token generateToken(String uid, String role) {
        // 30 min
        long accessPeriod = tokenValidityInMilliseconds;
        // 1 month
        long refreshPeriod = tokenValidityInMilliseconds * 2L * 24L * 30L;

        Claims claims = Jwts.claims().setSubject(uid);
        claims.put("role", role);

        Date now = new Date();

        String accessToken = createToken(claims, now, accessPeriod);
        String refreshToken = createToken(claims, now, refreshPeriod);

        return new Token(accessToken, refreshToken);
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUid(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public String createToken(Claims claims, Date now, long period) {
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + period))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }
}