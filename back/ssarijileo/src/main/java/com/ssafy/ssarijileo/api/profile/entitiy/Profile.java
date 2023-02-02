package com.ssafy.ssarijileo.api.profile.entitiy;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Profile {

	// PK (AUTO_INCREMENT)
	@Id
	String profileId;

	// 닉네임
	String nickname;

	// 프로필이미지
	String image;
}
