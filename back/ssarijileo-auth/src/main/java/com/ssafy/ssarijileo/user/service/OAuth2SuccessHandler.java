package com.ssafy.ssarijileo.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssarijileo.exception.NotFoundException;
import com.ssafy.ssarijileo.user.dto.Token;
import com.ssafy.ssarijileo.user.dto.UserDto;
import com.ssafy.ssarijileo.user.dto.UserRequestMapper;
import com.ssafy.ssarijileo.user.entity.User;
import com.ssafy.ssarijileo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final UserRequestMapper userRequestMapper;
    private final ObjectMapper objectMapper;
    private String redirectUrl = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);
        log.info("Principal에서 꺼낸 OAuth2User = {}", oAuth2User);

        Token tokens = new Token();

        // 회원 정보 받아옴
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(userDto.toUser(userDto));

        // 최초 로그인이라면 회원가입 처리를 한다.
        if (user.getToken() == null) {
            log.info("회원가입 해야됨");
            tokens = tokenProvider.generateToken(userDto.getEmail(), "ROLE_USER");
            user.updateToken(tokens.getRefreshToken());
            log.info("user token = {}",user.getToken());
            userRepository.save(user);
        } else {
            log.info("액세스 토큰만 발급");
            String access = tokenProvider.generateAccess(userDto.getEmail(), "ROLE_USER");
            tokens = tokens.builder().accessToken(access).refreshToken(user.getToken()).build();

            // 프로필 이미지 바뀌었으면 업데이트
            if (!(user.getImage().equals(userDto.getImage()))) {
                log.info("이미지 업데이트");
                user.updateImage(userDto.getImage());
            }
        }

        log.info("{}", tokens);

        String targetUrl;
        targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
                .queryParam("accessToken", tokens.getAccessToken())
                .queryParam("refreshToken", tokens.getRefreshToken())
                .build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);

        //writeTokenResponse(response, tokens);
    }

/*
    private void writeTokenResponse(HttpServletResponse response, Token token)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("accessToken", token.getAccessToken());
        response.addHeader("refreshToken", token.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        var writer = response.getWriter();
        writer.println(objectMapper.writeValueAsString(token));
        writer.flush();
    }
*/

}
