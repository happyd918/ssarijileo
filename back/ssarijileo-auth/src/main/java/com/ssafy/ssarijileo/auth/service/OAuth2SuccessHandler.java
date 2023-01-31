package com.ssafy.ssarijileo.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssarijileo.user.dto.Role;
import com.ssafy.ssarijileo.user.dto.TokenKey;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final UserRequestMapper userRequestMapper;
    private final RedisService redisService;
    private String redirectUrl = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException {
        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        UserDto userDto = userRequestMapper.toDto(oAuth2User);
        User guest = new User();

        Token tokens = new Token();

        // 회원 정보 받아옴
        User user = userRepository.findByEmail(userDto.getEmail()).orElse(guest);

        // 최초 로그인이라면 회원가입 처리를 한다.
        if (user.equals(guest)) {
            tokens = tokenProvider.generateToken(userDto.getEmail(), Role.USER.getKey());

            // 리프레시 토큰 캐시 저장 구문
            redisService.setDataWithExpiration(TokenKey.REFRESH.getKey(),
                tokens.getRefreshToken(), tokenProvider.getExpiration('R'));

            userRepository.save(userDto.toUser(userDto));
        } else {
            String access = tokenProvider.generateAccess(userDto.getEmail(), Role.USER.getKey());

            // 리프레시 토큰만 불러올 구문
            tokens = tokens.builder().accessToken(access)
                        .refreshToken(redisService.getData(TokenKey.REFRESH
                        .getKey())).build();

            // 프로필 이미지 바뀌었으면 업데이트
            if (!(user.getImage().equals(userDto.getImage()))) {
                log.info("이미지 업데이트");
                user.updateImage(userDto.getImage());
            }
        }

        String targetUrl;
        targetUrl = UriComponentsBuilder.fromUriString(redirectUrl)
            .queryParam(TokenKey.ACCESS.getKey(), "Bearer " + tokens.getAccessToken())
            .queryParam(TokenKey.REFRESH.getKey(), "Bearer " + tokens.getRefreshToken())
            .build().toUriString();

        // 프론트 페이지로 리다이렉트
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
