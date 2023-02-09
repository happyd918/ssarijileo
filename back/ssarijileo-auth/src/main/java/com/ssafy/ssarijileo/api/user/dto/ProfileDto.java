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
public class ProfileDto {
	private String profileId;
	private String nickname;
	private String image;

	public void updateUserId(String profileId) {
		this.profileId = profileId;
	}

}
