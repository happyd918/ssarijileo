package com.ssafy.ssarijileo.sse.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.friend.client.FriendClient;
import com.ssafy.ssarijileo.sse.service.SseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/sse")
public class SseController {

	private final SseService sseService;

	private final FriendClient friendClient;

	/**
	 * @title 로그인 한 사용자 SSE 연결
	 * @param response
	 * @param nickname
	 * @throws IOException
	 */
	@GetMapping(value = "/{nickname}")
	public void connection(HttpServletResponse response, @PathVariable String nickname) throws IOException {
		String userId = friendClient.findIdByNickname(nickname);
		sseService.connection(response, userId);
	}
}
