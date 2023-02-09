package com.ssafy.ssarijileo.api.profile.service;

import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;

public interface ProfileService {

	void insertProfile(ProfileDto profileDto);

	ProfileDto findProfileById(String userId);
}
