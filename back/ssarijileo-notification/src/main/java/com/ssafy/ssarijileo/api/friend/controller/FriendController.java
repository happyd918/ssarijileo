package com.ssafy.ssarijileo.api.friend.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.service.FriendService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	/**
	 * @title 친구 요청
	 * @param friendDto
	 */
	@PostMapping("/request")
	public void requestFriend(@RequestBody FriendDto friendDto) throws
		IOException {
		friendService.requestFriend(friendDto);
	}

	/**
	 * @title 친구 초대
	 * @param friendDto
	 */
	@PostMapping("/invite")
	public void inviteFriend(@RequestBody FriendDto friendDto) throws
		IOException {
		friendService.inviteFriend(friendDto);
	}
}
