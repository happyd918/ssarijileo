package com.ssafy.ssarijileo.sse.controller;

import java.util.Observable;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.kafka.event.FriendRequestEvent;
import com.ssafy.ssarijileo.kafka.producer.FriendInviteProducer;
import com.ssafy.ssarijileo.kafka.producer.FriendRequestProducer;
import com.ssafy.ssarijileo.sse.service.SseService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/v1/sse")
public class SseController {

	private final SseService sseService;

	private final FriendRequestProducer friendRequestProducer;
	private final FriendInviteProducer friendInviteProducer;

	/**
	 * @title 로그인 한 사용자 SSE 연결
	 * @param userId
	 * @return
	 */
	@GetMapping(value = "/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter connection(@PathVariable String userId) {
		System.out.println("sse : " + userId);
		return sseService.connection(userId);
	}

	/**
	 * @title 친구 요청
	 * @param friendRequestEvent
	 */
	@PostMapping
	void requestFriend(@RequestBody FriendRequestEvent friendRequestEvent) {
		System.out.println("sse req : " + friendRequestEvent.getFromUserNickname());

		// 친구 요청 알림
		friendRequestProducer.send(friendRequestEvent);
	}

	/**
	 * @title 친구 초대
	 * @param friendInviteEvent
	 */
	@PostMapping("/invite")
	void inviteFriend(@RequestBody FriendInviteEvent friendInviteEvent) {
		System.out.println("sse inv : " + friendInviteEvent.getFromUserNickname());

		// 친구 초대 알림
		friendInviteProducer.send(friendInviteEvent);
	}
}
