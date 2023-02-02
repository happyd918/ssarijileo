package com.ssafy.ssarijileo.api.profile.entitiy;

import java.util.UUID;

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
	private UUID profileId;

	// 닉네임
	private String nickname;

	// 프로필이미지
	private String image;
}
