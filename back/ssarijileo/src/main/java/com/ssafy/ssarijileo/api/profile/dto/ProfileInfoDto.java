package com.ssafy.ssarijileo.api.profile.dto;

import com.ssafy.ssarijileo.api.songsetting.dto.SongSettingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInfoDto {

	// PK
	String profileId;

	// 닉네임
	String nickname;

	// 프로필이미지
	String image;

	// 볼륨
	int volume;

	// 에코
	int eco;

	// @Builder
	// ProfileInfoDto(ProfileDto profileDto, SongSettingDto songSettingDto) {
	// 	this.profileId = profileDto.getProfileId();
	// 	this.nickname = profileDto.getNickname();
	// 	this.image = profileDto.getImage();
	// 	this.volume = songSettingDto.getVolume();
	// 	this.eco = songSettingDto.getEco();
	// }
}
