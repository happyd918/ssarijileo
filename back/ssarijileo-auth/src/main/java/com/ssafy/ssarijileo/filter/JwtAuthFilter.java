package com.ssafy.ssarijileo.filter;

import com.ssafy.ssarijileo.auth.dto.JwtCode;
import com.ssafy.ssarijileo.auth.dto.Role;
import com.ssafy.ssarijileo.auth.dto.Token;
import com.ssafy.ssarijileo.auth.dto.TokenKey;
import com.ssafy.ssarijileo.user.dto.UserDto;
import com.ssafy.ssarijileo.auth.service.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = tokenProvider.resolveToken(((HttpServletRequest)request).getHeader(TokenKey.ACCESS.getKey()));

        String userId = tokenProvider.getUid(token);
        if (token != null && tokenProvider.validateToken(token) == JwtCode.ACCESS) {

            UserDto userDto = UserDto.builder()
                    .userId(userId)
                    .build();

            Authentication auth = getAuthentication(userDto);
            SecurityContextHolder.getContext().setAuthentication(auth);
            log.info("set Authentication to security context for '{}', uri = {}", auth.getName(), ((HttpServletRequest)request).getRequestURI());
        } else if (token != null && tokenProvider.validateToken(token) == JwtCode.EXPIRED) {
            String refresh = tokenProvider.resolveToken(
                ((HttpServletRequest)request).getHeader(TokenKey.REFRESH.getKey()));
            String savedRefresh = tokenProvider.getSavedRefresh(userId);

            // refresh token을 확인해서 재발급
            if (token != null && refresh.equals(savedRefresh) && tokenProvider.validateToken(refresh) == JwtCode.ACCESS) {
                Token tokens = tokenProvider.generateToken(userId, Role.USER.getKey());

                tokenProvider.setSaveRefresh(userId,
                    tokens.getRefreshToken(), tokenProvider.getExpiration(TokenKey.REFRESH));

                ((HttpServletResponse)response).setHeader(TokenKey.ACCESS.getKey(), "Bearer " + tokens.getAccessToken());
                ((HttpServletResponse)response).setHeader(TokenKey.REFRESH.getKey(), "Bearer " + tokens.getRefreshToken());

                UserDto userDto = UserDto.builder()
                    .userId(userId)
                    .build();

                Authentication auth = getAuthentication(userDto);
                SecurityContextHolder.getContext().setAuthentication(auth);
                log.info("set Authentication to security context for '{}', uri = {}", auth.getName(), ((HttpServletRequest)request).getRequestURI());
            }
        } else {
            log.info("no valid JWT token found, uri: {}", ((HttpServletRequest)request).getRequestURI());
        }
        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(UserDto member) {
        return new UsernamePasswordAuthenticationToken(member, "",
                Arrays.asList(new SimpleGrantedAuthority(Role.USER.getKey())));
    }
}