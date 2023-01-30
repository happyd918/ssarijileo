package com.ssafy.ssarijileo.user.dto;

import com.ssafy.ssarijileo.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@Builder
public class UserDto {

    private String email;
    private String nickname;
    private String image;

    public User toUser(UserDto userDto) {
        return User.builder().email(userDto.getEmail()).nickname(userDto.getNickname()).image(userDto.getImage()).build();
    }
}
