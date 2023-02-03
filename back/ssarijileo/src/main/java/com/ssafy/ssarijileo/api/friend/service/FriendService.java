package com.ssafy.ssarijileo.api.friend.service;

import java.util.List;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;

public interface FriendService {

	List<FriendDto> findAllFriend();

	List<MyFriendDto> findFriendByUserId(String userId);

	void requestFriend(FriendDto friendDto);

	void updateFriend(FriendUpdateDto friendUpdateDto);

	void inviteFriend(FriendInviteDto friendInviteDto);
}
