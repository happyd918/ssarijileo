package com.ssafy.ssarijileo.user.dto;

import lombok.Getter;

@Getter
public enum RedisKey {
	REGISTER("Register_"), REFRESH("refreshToken");

	private String key;

	RedisKey(String key) {
		this.key = key;
	}
}