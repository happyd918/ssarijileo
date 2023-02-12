package com.ssafy.ssarijileo.sse.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.api.friend.client.FriendClient;
import com.ssafy.ssarijileo.sse.service.SseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/sse")
public class SseController {

	private final SseService sseService;

	private final FriendClient friendClient;

	/**
	 * @title 로그인 한 사용자 SSE 연결
	 * @param nickname
	 * @return
	 */
	@GetMapping(value = "/{nickname}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter connection(@PathVariable String nickname) {
		System.out.println("sse connect nickname : " + nickname);

		String userId = friendClient.findIdByNickname(nickname);

		System.out.println("sse connect userId : " + userId);
		return sseService.connection(userId);
	}
}
