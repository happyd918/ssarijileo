package com.ssafy.ssarijileo.user.controller;

import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class AuthController {
    private final TokenProvider tokenProvider;

    @GetMapping("/token/expired")
    public String auth() {
       throw new RuntimeException();
    }

    @GetMapping("/token/refresh")
    public String refreshAuth(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Refresh");

        if (token != null && tokenProvider.verifyToken(token)) {
            String email = tokenProvider.getUid(token);
            Token newToken = tokenProvider.generateToken(email, "USER");

            response.addHeader("Access", newToken.getAccessToken());
            response.addHeader("Refresh", newToken.getRefreshToken());
            response.setContentType("application/json;charset=UTF-8");

            return "NEW ACCESS TOKEN";
        }

        throw new RuntimeException();
    }
}