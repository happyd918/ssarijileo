package com.ssafy.ssarijileo.common.kafka.event;

import com.ssafy.ssarijileo.common.model.AlarmUser;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendRequestEvent {

	AlarmUser user;

	// 보낸사람 닉네임
	String from_user_nickname;

	// 친구PK
	Long friendId;
}
