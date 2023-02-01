package com.ssafy.ssarijileo.auth.dto;

import lombok.Getter;

@Getter
public enum JwtCode {
	DENIED,
	ACCESS,
	EXPIRED;
}
