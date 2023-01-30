package com.ssafy.ssarijileo.friend.service;

import java.util.List;

import com.ssafy.ssarijileo.friend.dto.FriendDto;
import com.ssafy.ssarijileo.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.friend.dto.MyFriendDto;

public interface FriendService {

	List<FriendDto> findAllFriend();

	List<MyFriendDto> findFriendByUserId(String userId);

	void insertFriend(FriendDto friendDto);

	void updateFriend(FriendUpdateDto friendUpdateDto);
}
