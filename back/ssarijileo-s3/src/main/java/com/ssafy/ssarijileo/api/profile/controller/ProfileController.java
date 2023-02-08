package com.ssafy.ssarijileo.api.profile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.profile.dto.ProfileDto;
import com.ssafy.ssarijileo.api.profile.service.ProfileService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = "프로필 API")
@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
public class ProfileController {

	private final ProfileService profileService;

	/**
	 * @title 사용자 프로필 정보 저장
	 * @param profileDto
	 */
	@ApiOperation(
		value = "사용자 프로필 정보 저장",
		notes = "로그인 후 자동으로 사용자 프로필 정보를 저장한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> insertSinging(@RequestBody ProfileDto profileDto) {
		profileService.insertProfile(profileDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
