package com.ssafy.ssarijileo.user.dto;

import lombok.Getter;

@Getter
public enum RedisKey {
	REFRESH("refreshToken");

	private String key;

	RedisKey(String key) {
		this.key = key;
	}
}