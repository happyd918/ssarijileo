package com.ssafy.ssarijileo.sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.kafka.event.FriendRequestEvent;

public interface SseService {

	SseEmitter connection(String userId);

	void sendFriendRequest(FriendRequestEvent event);

	void sendFriendInvite(FriendInviteEvent event);
}
