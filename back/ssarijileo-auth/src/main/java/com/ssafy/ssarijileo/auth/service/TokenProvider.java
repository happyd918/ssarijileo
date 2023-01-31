package com.ssafy.ssarijileo.user.service;

import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.dto.TokenKey;

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
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

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

    public String generateAccess(String uid, String role) {
        return createToken(uid, role, 'A');
    }

    public String generateRefresh(String uid, String role) {
        return createToken(uid, role, 'R');
    }

    public Token generateToken(String uid, String role) {
        String accessToken = createToken(uid, role, 'A');
        String refreshToken = createToken(uid, role, 'R');

        return new Token(accessToken, refreshToken);
    }

    public boolean verifyToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(token);
            return claims.getBody()
                    .getExpiration()
                    .after(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public String getUid(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Long getExpiration(char state) {
        switch(state) {
            case 'A' : return tokenValidityInMilliseconds;
            case 'R' : return tokenValidityInMilliseconds * 2L * 24L * 30;
            default : throw new RuntimeException();
        }
    }

    public String createToken(String uid, String role, char state) {    // state -> 'A' : Access, 'R' : Refresh
        long period;
        // access : 30 min, refresh : 1 month
        switch(state) {
            case 'A' : period = tokenValidityInMilliseconds; break;
            case 'R' : period = tokenValidityInMilliseconds * 2L * 24L * 30; break;
            default : throw new RuntimeException();
        }

        Claims claims = Jwts.claims().setSubject(uid);
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + period))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}