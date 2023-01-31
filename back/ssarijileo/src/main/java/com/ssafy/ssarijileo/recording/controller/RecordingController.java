package com.ssafy.ssarijileo.recording.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.recording.service.RecordingService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
	@GetMapping
	public List<RecordingDto> findAllRecording(){
		return recordingService.findAllRecording();
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
	@GetMapping("{id}")
	public RecordingDto findRecordingById(@PathVariable Long id){
		return recordingService.findRecordingById(id);
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
	@GetMapping("/my/{userId}")
	public List<RecordingDto> findRecordingByUserId(@PathVariable  String userId) {
		return recordingService.findRecordingByUserId(userId);
	}

	/**
	 * 녹화 정보 저장
	 * @param recordingDto
	 */
	@ApiOperation(
		value = "녹화 정보 저장",
		notes = "녹화 정보를 저장한다."
	)
	@PostMapping
	public void insertRecording(@RequestBody RecordingDto recordingDto){
		recordingService.insertRecording(recordingDto);
	}
}
