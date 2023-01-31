package com.ssafy.ssarijileo.user.controller;

import java.util.Map;

import com.ssafy.ssarijileo.user.dto.Role;
import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.dto.TokenKey;
import com.ssafy.ssarijileo.user.service.TokenProvider;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController("/token")
public class AuthController {
    private final TokenProvider tokenProvider;

    @GetMapping("/expired")
    public String auth() {
       throw new RuntimeException();
    }

    @GetMapping("/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader(TokenKey.REFRESH.getKey());

        if (token != null && tokenProvider.verifyToken(token)) {
            String email = tokenProvider.getUid(token);
            Token newToken = tokenProvider.generateToken(email, Role.USER.getKey());

            response.addHeader(TokenKey.ACCESS.getKey(), newToken.getAccessToken());
            response.addHeader(TokenKey.REFRESH.getKey(), newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");

            return "NEW TOKEN";
        }

        throw new RuntimeException();
    }



}