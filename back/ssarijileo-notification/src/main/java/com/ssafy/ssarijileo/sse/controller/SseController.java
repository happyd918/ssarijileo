package com.ssafy.ssarijileo.sse.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.common.model.FriendDto;
import com.ssafy.ssarijileo.common.model.FriendInviteDto;
import com.ssafy.ssarijileo.sse.service.SseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/sse", produces = MediaType.APPLICATION_JSON_VALUE)
public class SseController {

	private final SseService sseService;

	/**
	 * @title 로그인 한 사용자 SSE 연결
	 * @param userId
	 * @return
	 */
	@GetMapping("{userId}")
	public SseEmitter connection(@PathVariable String userId) {
		System.out.println("sse connection : " + userId);
		return sseService.connection(userId);
	}

	/**
	 * @title 친구 요청
	 * @param friendDto
	 */
	@PostMapping
	void requestFriend(@RequestBody FriendDto friendDto) {

	}

	/**
	 * @title 친구 초대
	 * @param friendInviteDto
	 */
	@PostMapping("/invite")
	void inviteFriend(@RequestBody FriendInviteDto friendInviteDto) {

	}

}
