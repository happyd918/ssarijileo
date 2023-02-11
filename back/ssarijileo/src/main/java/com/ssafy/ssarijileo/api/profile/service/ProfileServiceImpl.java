package com.ssafy.ssarijileo.api.profile.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.api.profile.client.ProfileClient;
import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;
import com.ssafy.ssarijileo.api.profile.dto.ProfileInfoDto;
import com.ssafy.ssarijileo.api.profile.entitiy.Profile;
import com.ssafy.ssarijileo.api.profile.repository.ProfileJpaRepository;
import com.ssafy.ssarijileo.api.songsetting.entity.SongSetting;
import com.ssafy.ssarijileo.api.songsetting.repository.SongSettingJpaRepository;
import com.ssafy.ssarijileo.api.songsetting.service.SongSettingService;
import com.ssafy.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ProfileServiceImpl implements ProfileService {

	private final ProfileJpaRepository profileJpaRepository;
	private final SongSettingJpaRepository songSettingJpaRepository;

	private final ProfileClient profileClient;

	@Override
	public SseEmitter connection(String userId) {
		// 알림을 위한 SSE 연결
		System.out.println("rep : " + userId);
		System.out.println(profileClient.connection(userId));
		return null;
		// return profileClient.connection(userId);
	}

	@Override
	public void insertProfile(ProfileDto profileDto) {
		log.info("profileDto id = {}", profileDto.getProfileId());
		Profile profile = Profile.builder().profileDto(profileDto).build();
		log.info("profile id = {}", profile.getProfileId());
		profileJpaRepository.save(profile);

		SongSetting songSetting = SongSetting.builder().userId(profileDto.getProfileId()).build();
		songSettingJpaRepository.save(songSetting);

	}

	@Override
	public ProfileInfoDto findProfileById(String userId) {
		return profileJpaRepository.findById(userId).orElseThrow(NotFoundException::new).toDto();
	}

	@Override
	public void updateProfile(ProfileInfoDto profileInfoDto) {
		Profile profile = profileJpaRepository.findById(profileInfoDto.getProfileId())
			.orElseThrow(NotFoundException::new);
		profile.updateNickname(profileInfoDto.getNickname());
		profileJpaRepository.save(profile);

		SongSetting songSetting = songSettingJpaRepository.findById(profileInfoDto.getProfileId())
			.orElseThrow(NotFoundException::new);
		songSetting.updateSetting(profileInfoDto.getEco(), profileInfoDto.getVolume());
		songSettingJpaRepository.save(songSetting);
	}

	@Override
	public void updateImage(ProfileDto profileDto) {
		Profile profile = profileJpaRepository.findById(profileDto.getProfileId()).orElseThrow(NotFoundException::new);
		profile.updateImage(profile.getImage());
		profileJpaRepository.save(profile);
	}

	@Override
	public boolean checkNickname(String nickname) {
		Profile profile = new Profile();
		// 비었다면 이름 변경 가능 -> true
		Profile profile2 = profileJpaRepository.findByNickname(nickname).orElse(profile);
		log.info("profile equals = {}", profile.equals(profile2));
		log.info("profile equals = {}", profile2.getNickname());
		return profile.equals(profile2);
	}
}
