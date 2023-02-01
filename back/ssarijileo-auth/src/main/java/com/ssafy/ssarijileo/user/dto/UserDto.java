package com.ssafy.ssarijileo.user.dto;

import java.util.UUID;

import com.ssafy.ssarijileo.user.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String userId;
    private String socialId;
    private String status;

    public User toUser(UserDto userDto) {
        return User.builder().socialId(userDto.getSocialId()).build();
    }
}
