package com.ssafy.ssarijileo.api.profile.service;

import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;
import com.ssafy.ssarijileo.api.profile.dto.ProfileInfoDto;

public interface ProfileService {

	void insertProfile(ProfileDto profileDto);

	ProfileInfoDto findProfileById(String userId);

	void updateProfile(ProfileInfoDto profileInfoDto);

	void updateImage(ProfileDto profileDto);
}
