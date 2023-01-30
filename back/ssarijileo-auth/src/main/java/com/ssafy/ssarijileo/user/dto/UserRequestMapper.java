package com.ssafy.ssarijileo.user.dto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserRequestMapper {
    public UserDto toDto(OAuth2User oAuth2User) {
        var attributes = oAuth2User.getAttributes();
        log.info("oAuth2User attributes = {}", attributes);

        return UserDto.builder()
                .email((String)attributes.get("email"))
                .nickname((String)attributes.get("nickname"))
                .image((String)attributes.get("image"))
                .build();
    }
}