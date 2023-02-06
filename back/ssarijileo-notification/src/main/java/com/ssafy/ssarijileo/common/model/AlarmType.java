package com.ssafy.ssarijileo.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AlarmType {
	FRIEND_REQUEST("friend request"),
	FRIEND_INVITE("friend invite");

	private final String alarmType;
}
