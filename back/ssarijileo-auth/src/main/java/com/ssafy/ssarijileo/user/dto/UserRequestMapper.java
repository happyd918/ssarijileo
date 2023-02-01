package com.ssafy.ssarijileo.user.dto;

import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.ssafy.ssarijileo.user.entity.User;

@Slf4j
@Component
public class UserRequestMapper {
    public UserInfoDto toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        log.info("oAuth2User attributes = {}", attributes);

        return UserInfoDto.builder()
                .image(String.valueOf(attributes.get("image")))
                .nickname(String.valueOf(attributes.get("nickname")))
                .build();
    }
}