package com.ssafy.ssarijileo.auth.service;

import com.ssafy.ssarijileo.auth.dto.JwtCode;
import com.ssafy.ssarijileo.auth.dto.Token;
import com.ssafy.ssarijileo.auth.dto.TokenKey;
import com.ssafy.ssarijileo.user.dto.UserInfoDto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.util.Date;

@Service
@Slf4j
public class TokenProvider implements InitializingBean {
    private final String secret;
    private final long tokenValidityInMilliseconds;     // 30 min
    private final RedisService redisService;
    private Key key;

    @Autowired
    public TokenProvider(
        @Value("${jwt.secret}")String secret,
        @Value("${jwt.token-validity-in-seconds}")long tokenValidityInSeconds,
        RedisService redisService
    ) {
        this.secret = secret;
        this.tokenValidityInMilliseconds = tokenValidityInSeconds * 1000;
        this.redisService = redisService;
    }

    @Override
    public void afterPropertiesSet() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String getSavedRefresh(String key) {
        return redisService.getData(key);
    }

    public void setSaveRefresh(String key, String value, Long time) {
        redisService.setDataWithExpiration(key, value, time);
    }

    public String generateAccess(UserInfoDto userInfo, String role) {
        return createToken(userInfo, role, TokenKey.ACCESS);
    }

    public String generateRefresh(UserInfoDto userInfo, String role) {
        return createToken(userInfo, role, TokenKey.REFRESH);
    }

    public Token generateToken(UserInfoDto userInfo, String role) {
        String accessToken = generateAccess(userInfo, role);
        String refreshToken = generateRefresh(userInfo, role);

        return new Token(accessToken, refreshToken);
    }

    public String createToken(UserInfoDto userInfo, String role, TokenKey tokenKey) {
        // access : 30 min, refresh : 1 month
        long period = getExpiration(tokenKey);

        Claims claims = Jwts.claims().setSubject(userInfo.getUserId());
        claims.put("nickname", userInfo.getNickname());
        claims.put("image", userInfo.getImage());
        claims.put("role", role);

        Date now = new Date();

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(new Date(now.getTime() + period))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }

    public JwtCode validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token);
            return JwtCode.ACCESS;
        } catch (ExpiredJwtException e) {
            // 만료된 경우에는 refresh token을 확인하기 위해
            return JwtCode.EXPIRED;
        } catch (JwtException | IllegalArgumentException  e) {
            log.info("jwtException = {}", e);
        }
        return JwtCode.DENIED;
    }

/*
    public boolean validateToken(String token) {
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
*/

    public String getUid(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Claims getClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    public Long getExpiration(TokenKey tokenKey) {

        String delimiter = tokenKey.getKey();
        if (delimiter.equals(TokenKey.ACCESS.getKey())) {
            return tokenValidityInMilliseconds;
        } else if (delimiter.equals(TokenKey.REFRESH.getKey())) {
            return tokenValidityInMilliseconds * 2L * 24L * 30;
        } else {
            throw new RuntimeException();
        }
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}