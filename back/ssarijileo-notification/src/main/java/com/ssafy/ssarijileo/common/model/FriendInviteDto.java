package com.ssafy.ssarijileo.common.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FriendInviteDto {

	// 보낸 사용자PK
	String fromUserNickname;

	// 받는 사용자PK
	String toUserNickname;

	// 노래방 세션ID
	String sessionId;
}
