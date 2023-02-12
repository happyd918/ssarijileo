package com.ssafy.ssarijileo.api.friend.service;

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
	public void requestFriend(FriendDto friendDto) {
		String fromUserId = friendClient.findIdByNickname(friendDto.getFromUserNickname());
		String toUserId = friendClient.findIdByNickname(friendDto.getToUserNickname());
		AlarmUser user = new AlarmUser(fromUserId, toUserId);
		FriendRequestEvent event = FriendRequestEvent.builder()
			.user(user).fromUserNickname(friendDto.getFromUserNickname())
			.friendId(friendDto.getFriendId()).build();

		System.out.println("req : " + user.getToUserId());
		sseService.sendFriendRequest(event);
	}

	@Override
	public void inviteFriend(FriendDto friendDto) {
		String fromUserId = friendClient.findIdByNickname(friendDto.getFromUserNickname());
		String toUserId = friendClient.findIdByNickname(friendDto.getToUserNickname());
		AlarmUser user = new AlarmUser(fromUserId, toUserId);
		FriendInviteEvent event = FriendInviteEvent.builder()
			.user(user).fromUserNickname(friendDto.getFromUserNickname())
			.sessionId(friendDto.getSessionId()).build();

		System.out.println("inv : " + user.getToUserId());
		sseService.sendFriendInvite(event);
	}
}
