package com.ssafy.ssarijileo.sse.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.api.friend.dto.FriendInviteEvent;
import com.ssafy.ssarijileo.api.friend.dto.FriendRequestEvent;

public interface SseService {

	void connection(HttpServletResponse response, String userId) throws IOException;

	void sendFriendRequest(FriendRequestEvent event) throws IOException;

	void sendFriendInvite(FriendInviteEvent event) throws IOException;
}
