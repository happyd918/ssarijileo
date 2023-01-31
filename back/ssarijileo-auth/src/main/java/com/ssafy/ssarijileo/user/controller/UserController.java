package com.ssafy.ssarijileo.user.controller;

import com.ssafy.ssarijileo.user.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

public class UserController {


    /**
     * 토큰으로 유저정보 요청 들어옴
     * 분기
     * 1) 액세스 유효 -> 바로 줌
     * 2) 액세스 만료
     * 2-1) 리프레시 유효 -> 괜찮네? 회원정보랑 액세스 줌
     * 2-2) 리프레시 만료 -> 로그아웃 시킴
     *
     */
    @GetMapping("/userInfo")
    public ResponseEntity<UserDto> getUserInfo(HttpServletRequest request) {
        UserDto userDto = new UserDto();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
