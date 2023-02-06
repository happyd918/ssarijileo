package com.ssafy.ssarijileo.api.room.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.room.dto.RoomDto;
import com.ssafy.ssarijileo.api.room.dto.RoomRequestDto;
import com.ssafy.ssarijileo.api.room.dto.RoomResponseDto;
import com.ssafy.ssarijileo.api.room.service.RoomService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(tags = "노래방 API")
@RestController
@RequestMapping("/api/v1/room")
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	/**
	 * @title 방 목록
	 * @return
	 */
	@ApiOperation(
		value = "방 목록",
		notes = "노래방 전체 목록을 조회한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "노래방 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@GetMapping
	ResponseEntity<List<RoomResponseDto>> findAllRoom() {
		return ResponseEntity.status(200).body(roomService.findAllRoom());
	}

	/**
	 * @title 방 생성
	 * @param roomDto
	 * @return
	 */
	@ApiOperation(
		value = "방 생성",
		notes = "세션 ID를 랜덤으로 생성하여 해당 세션의 노래방을 만든다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PostMapping
	ResponseEntity<? extends BaseResponseBody> createRoom(@RequestBody RoomDto roomDto) {
		roomService.createRoom(roomDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	/**
	 * @title 방 입장
	 * @param roomRequestDto
	 * @return
	 */
	@ApiOperation(
		value = "방 입장",
		notes = "세션 ID를 통해 해당 세션의 노래방에 입장한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PutMapping
	ResponseEntity<? extends BaseResponseBody> enterRoom(@RequestBody RoomRequestDto roomRequestDto) {
		roomService.enterRoom(roomRequestDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	/**
	 * @title 방 퇴장
	 * @param roomRequestDto
	 * @return
	 */
	@ApiOperation(
		value = "방 퇴장",
		notes = "세션 ID를 통해 해당 세션의 노래방에서 퇴장한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@DeleteMapping
	ResponseEntity<? extends BaseResponseBody> leaveRoom(@RequestBody RoomRequestDto roomRequestDto) {
		roomService.leaveRoom(roomRequestDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}
}
