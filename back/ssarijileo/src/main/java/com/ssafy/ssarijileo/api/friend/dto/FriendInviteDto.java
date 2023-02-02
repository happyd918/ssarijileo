package com.ssafy.ssarijileo.api.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendInviteDto {

	// 보낸 사용자PK
	String fromUserId;

	// 받는 사용자PK
	String toUserId;

	// 노래방 링크
	String link;
}
