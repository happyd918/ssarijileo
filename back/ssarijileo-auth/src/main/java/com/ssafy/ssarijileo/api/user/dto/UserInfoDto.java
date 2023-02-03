package com.ssafy.ssarijileo.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoDto {
	private String userId;
	private String nickname;
	private String image;

	public void updateUserId(String userId) {
		this.userId = userId;
	}

}