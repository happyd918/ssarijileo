package com.ssafy.ssarijileo.friend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MyFriendDto {

	// PK (AUTO_INCREMENT)
	Long friendId;

	// 친구 사용자PK
	String userId;

	// 상태(W:대기,A:수락,X:취소)
	char status;
}
