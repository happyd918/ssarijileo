package com.ssafy.ssarijileo.api.profile.entitiy;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;
import com.ssafy.ssarijileo.api.songsetting.entity.SongSetting;

import lombok.AllArgsConstructor;
import lombok.Builder;
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

	// Dto to Entity
	@Builder
	public Profile(ProfileDto profileDto) {
		this.profileId = profileDto.getProfileId();
		this.nickname = profileDto.getNickname();
		this.image = profileDto.getImage();
	}
}
