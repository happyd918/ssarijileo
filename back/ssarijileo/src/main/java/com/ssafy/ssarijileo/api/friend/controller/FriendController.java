package com.ssafy.ssarijileo.api.friend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.service.FriendService;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/friend")
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	/**
	 * 내 친구 목록
	 * @param userId
	 * @return
	 */
	@ApiOperation(
		value = "내 친구 목록",
		notes = "사용자 ID를 통해 해당 사용자의 친구 목록을 조회한다."
	)
	@ApiImplicitParam(
		name = "userId",
		value = "사용자 PK"
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "친구 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@GetMapping("/my/{userId}")
	public ResponseEntity<List<MyFriendDto>> findFriendByUserId(@PathVariable  String userId) {
		return ResponseEntity.status(200).body(friendService.findFriendByUserId(userId));
	}

	/**
	 * 친구 요청
	 * @param friendDto
	 */
	@ApiOperation(
		value = "친구 요청",
		notes = "다른 사용자에게 친구를 요청한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PostMapping
	public ResponseEntity<? extends BaseResponseBody> requestFriend(@RequestBody FriendDto friendDto) {
		friendService.requestFriend(friendDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

	/**
	 * 친구 수락 및 취소
	 * @param friendUpdateDto
	 */
	@ApiOperation(
		value = "친구 수락 및 취소",
		notes = "친구 요청을 수락하거나 취소한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PutMapping
	public ResponseEntity<? extends BaseResponseBody> updateFriend(@RequestBody FriendUpdateDto friendUpdateDto) {
		friendService.updateFriend(friendUpdateDto);
		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
	}

<<<<<<< back/ssarijileo/src/main/java/com/ssafy/ssarijileo/api/friend/controller/FriendController.java
	/**
	 * 친구 초대
	 * @param friendInviteDto
	 */
	@ApiOperation(
		value = "친구 초대",
		notes = "노래방에 친구를 초대한다."
	)
	@ApiResponses({
		@ApiResponse(code = 200, message = "성공"),
		@ApiResponse(code = 401, message = "인증 실패"),
		@ApiResponse(code = 404, message = "정보 없음"),
		@ApiResponse(code = 500, message = "서버 오류")
	})
	@PostMapping("/invite")
	public ResponseEntity<? extends BaseResponseBody> inviteFriend(@RequestBody FriendInviteDto friendInviteDto) {
		friendService.inviteFriend(friendInviteDto);
	}
=======
	
}
