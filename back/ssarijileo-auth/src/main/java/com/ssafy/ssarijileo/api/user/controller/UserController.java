package com.ssafy.ssarijileo.api.user.controller;

import com.ssafy.ssarijileo.api.user.dto.UserDto;
import com.ssafy.ssarijileo.api.user.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/userInfo")
    public ResponseEntity<UserDto> findUserInfo(String userId) {
        UserDto userDto = new UserDto();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
