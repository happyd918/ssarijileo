package com.ssafy.ssarijileo.user.controller;

import java.util.Map;

import com.ssafy.ssarijileo.user.dto.Role;
import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.dto.TokenKey;
import com.ssafy.ssarijileo.user.service.TokenProvider;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/token")
public class AuthController {

    private final TokenProvider tokenProvider;

    /**
     * 토큰 시간 만료 시 로직
     * @return
     */
    @ApiOperation(
        value = "토큰 만료 시",
        notes = "토큰 만료 시 로직 ~~"
    )
    @GetMapping("/expired")
    public String auth() {
       throw new RuntimeException();
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @ApiOperation(
        value = "리프레시 토큰 검증",
        notes = "리프레시 토큰을 검증 하여 토큰 재발급"
    )
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