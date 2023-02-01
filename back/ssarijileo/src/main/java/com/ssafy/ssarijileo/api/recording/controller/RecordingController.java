package com.ssafy.ssarijileo.api.recording.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.api.recording.service.RecordingService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/recording")
@RequiredArgsConstructor
public class RecordingController {

	private final RecordingService recordingService;

	/**
	 * 녹화 전체 목록 조회
	 * @return
	 */
	@ApiOperation(
		value = "녹화 전체 목록 조회",
		notes = "녹화 전체 목록을 조회한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "녹화 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@GetMapping
	public ResponseEntity<List<RecordingDto>> findAllRecording(){
		return ResponseEntity.status(200).body(recordingService.findAllRecording());
	}

	/**
	 * 녹화 정보 조회
	 * @param id
	 * @return
	 */
	@ApiOperation(
		value = "녹화 정보 조회",
		notes = "녹화 ID를 통해 녹화 정보를 조회한다."
	)
	@ApiImplicitParam(
		name = "id",
		value = "녹화 PK"
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "녹화 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@GetMapping("{id}")
	public ResponseEntity<RecordingDto> findRecordingById(@PathVariable Long id){
		return ResponseEntity.status(200).body(recordingService.findRecordingById(id));
	}

	/**
	 * 내 녹화 목록 조회
	 * @param userId
	 * @return
	 */
	@ApiOperation(
		value = "내 녹화 목록 조회",
		notes = "사용자 ID를 통해 해당 사용자의 녹화 목록을 조회한다."
	)
	@ApiImplicitParam(
		name = "userId",
		value = "사용자 PK"
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "녹화 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@GetMapping("/my/{userId}")
	public ResponseEntity<List<RecordingDto>> findRecordingByUserId(@PathVariable  String userId) {
		return ResponseEntity.status(200).body(recordingService.findRecordingByUserId(userId));
	}

	/**
	 * 녹화 정보 저장
	 * @param recordingDto
	 */
	@ApiOperation(
		value = "녹화 정보 저장",
		notes = "녹화 정보를 저장한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> insertRecording(@RequestBody RecordingDto recordingDto){
		recordingService.insertRecording(recordingDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
