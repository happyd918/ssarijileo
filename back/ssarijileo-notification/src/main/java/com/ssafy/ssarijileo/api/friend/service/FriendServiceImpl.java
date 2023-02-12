package com.ssafy.ssarijileo.api.friend.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.friend.client.FriendClient;
import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.common.model.AlarmUser;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteEvent;
import com.ssafy.ssarijileo.api.friend.dto.FriendRequestEvent;
import com.ssafy.ssarijileo.sse.service.SseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

	private final FriendClient friendClient;

	private final SseService sseService;

	@Override
	public void requestFriend(FriendDto friendDto) throws IOException {
		String fromUserId = friendClient.findIdByNickname(friendDto.getFromUserNickname());
		String toUserId = friendClient.findIdByNickname(friendDto.getToUserNickname());
		AlarmUser user = new AlarmUser(fromUserId, toUserId);
		FriendRequestEvent event = FriendRequestEvent.builder()
			.user(user).fromUserNickname(friendDto.getFromUserNickname())
			.friendId(friendDto.getFriendId()).build();

		sseService.sendFriendRequest(event);
	}

	@Override
	public void inviteFriend(FriendDto friendDto) throws IOException {
		String fromUserId = friendClient.findIdByNickname(friendDto.getFromUserNickname());
		String toUserId = friendClient.findIdByNickname(friendDto.getToUserNickname());
		AlarmUser user = new AlarmUser(fromUserId, toUserId);
		FriendInviteEvent event = FriendInviteEvent.builder()
			.user(user).fromUserNickname(friendDto.getFromUserNickname())
			.sessionId(friendDto.getSessionId()).build();

		sseService.sendFriendInvite(event);
	}
}
