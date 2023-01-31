package com.ssafy.ssarijileo.user.dto;

import lombok.Getter;

@Getter
public enum TokenKey {
	ACCESS("accessToken"), REFRESH("refreshToken");

	private String key;

	TokenKey(String key) {
		this.key = key;
	}
}