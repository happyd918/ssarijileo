package com.ssafy.ssarijileo.kafka.event;

import com.ssafy.ssarijileo.common.model.AlarmUser;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FriendInviteEvent {

	AlarmUser user;

	// 보낸사람 닉네임
	String from_user_nickname;

	// 노래방 링크
	String link;
}
