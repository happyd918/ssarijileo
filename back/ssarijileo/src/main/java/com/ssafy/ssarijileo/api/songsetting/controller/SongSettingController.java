package com.ssafy.ssarijileo.api.songsetting.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.songsetting.dto.SongSettingDto;
import com.ssafy.ssarijileo.api.songsetting.service.SongSettingService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/song-setting")
@AllArgsConstructor
public class SongSettingController {

	private final SongSettingService songSettingService;

	@GetMapping("/my/{userId}")
	ResponseEntity<SongSettingDto> findSongSettingByUserId(@PathVariable String userId) {
		return ResponseEntity.status(200).body(songSettingService.findSongSettingByUserId(userId));
	}

	@PutMapping
	ResponseEntity<? extends BaseResponseBody> updateSongSetting(@RequestBody SongSettingDto songSettingDto) {
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
