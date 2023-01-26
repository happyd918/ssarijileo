package com.ssafy.ssarijileo.user.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class UserDto {

    private String email;
    private String nickname;

    @Builder
    public UserDto(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }
}
