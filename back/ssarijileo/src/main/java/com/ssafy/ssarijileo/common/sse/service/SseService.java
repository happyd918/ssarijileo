package com.ssafy.ssarijileo.common.sse.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.common.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.common.kafka.event.FriendRequestEvent;

public interface SseService {

	SseEmitter connection(String userId);

	void sendFriendRequest(FriendRequestEvent event);

	void sendFriendInvite(FriendInviteEvent event);
}
