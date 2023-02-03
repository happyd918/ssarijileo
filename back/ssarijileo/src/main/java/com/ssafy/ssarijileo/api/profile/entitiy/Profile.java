package com.ssafy.ssarijileo.api.profile.entitiy;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

	// PK
	@Id
	private String profileId;

	// 닉네임
	private String nickname;

	// 프로필이미지
	private String image;
}
