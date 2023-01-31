package com.ssafy.ssarijileo.friend.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.exception.NotFoundException;
import com.ssafy.ssarijileo.friend.dto.FriendDto;
import com.ssafy.ssarijileo.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.friend.entity.Friend;
import com.ssafy.ssarijileo.friend.repository.FriendJpaRepository;
import com.ssafy.ssarijileo.friend.repository.FriendRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService{

	private final FriendJpaRepository friendJpaRepository;
	private final FriendRepository friendRepository;

	@Override
	public List<FriendDto> findAllFriend() {
		return friendJpaRepository.findAll().stream().map(Friend::toDto).collect(Collectors.toList());
	}

	@Override
	public List<MyFriendDto> findFriendByUserId(String userId) {
		return friendRepository.findFriendByUserId(userId).orElseThrow(NotFoundException::new);
	}

	@Override
	public void insertFriend(FriendDto friendDto) {
		Friend friend = Friend.builder().friendDto(friendDto).build();
		friendJpaRepository.save(friend);
	}

	@Override
	public void updateFriend(FriendUpdateDto friendUpdateDto) {
		Friend friend = friendJpaRepository.findById(friendUpdateDto.getFriendId()).orElseThrow(NotFoundException::new);
		friend.updateFriend(friendUpdateDto.getStatus());
	}
}
