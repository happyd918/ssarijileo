package com.ssafy.ssarijileo.friend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.friend.dto.FriendDto;
import com.ssafy.ssarijileo.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.friend.service.FriendService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {

	private final FriendService friendService;

	/**
	 * 친구 전체 목록 조회
	 * @return
	 */
	@ApiOperation(
		value = "친구 전체 목록 조회",
		notes = "친구 전체 목록을 조회한다."
	)
	@GetMapping
	public List<FriendDto> findAllFriend() {
		return friendService.findAllFriend();
	}

	/**
	 * 내 친구 목록 조회
	 * @param userId
	 * @return
	 */
	@ApiOperation(
		value = "내 친구 목록 조회",
		notes = "사용자 ID를 통해 해당 사용자의 친구 목록을 조회한다."
	)
	@ApiImplicitParam(
		name = "userId",
		value = "사용자 PK"
	)
	@GetMapping("/my/{userId}")
	public List<MyFriendDto> findFriendByUserId(@PathVariable  String userId) {
		return friendService.findFriendByUserId(userId);
	}

	/**
	 * 친구 요청
	 * @param friendDto
	 */
	@ApiOperation(
		value = "친구 요청",
		notes = "다른 사용자에게 친구를 요청한다."
	)
	@PostMapping
	public void insertFriend(@RequestBody FriendDto friendDto) {
		friendService.insertFriend(friendDto);
	}

	/**
	 * 친구 수락 및 취소
	 * @param friendUpdateDto
	 */
	@ApiOperation(
		value = "친구 수락 및 취소",
		notes = "친구 요청을 수락하거나 취소한다."
	)
	@PutMapping
	public void updateFriend(@RequestBody FriendUpdateDto friendUpdateDto) {
		friendService.updateFriend(friendUpdateDto);
	}
}
