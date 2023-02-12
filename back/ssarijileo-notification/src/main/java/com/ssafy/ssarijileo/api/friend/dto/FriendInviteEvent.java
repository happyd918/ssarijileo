package com.ssafy.ssarijileo.api.friend.dto;

import com.ssafy.ssarijileo.common.model.AlarmUser;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendInviteEvent {

	AlarmUser user;

	// 보낸사람 닉네임
	String fromUserNickname;

	// 노래방 세션ID
	String sessionId;
}
