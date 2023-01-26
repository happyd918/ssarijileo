package com.ssafy.ssarijileo.user.dto;

import com.ssafy.ssarijileo.user.entity.User;
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
    private String image;

//    @Builder
//    public UserDto(String email, String nickname) {
//        this.email = email;
//        this.nickname = nickname;
//    }

    @Builder
    public UserDto(String email, String nickname, String image) {
        this.email = email;
        this.nickname = nickname;
        this.image = image;
    }

    public User toUser(UserDto userDto) {
        return User.builder().email(userDto.getEmail()).nickname(userDto.getNickname()).image(userDto.getImage()).build();
    }
}
