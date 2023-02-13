package com.ssafy.ssarijileo.api.friend.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;

public interface FriendService {

	void requestFriend(FriendDto friendDto) throws IOException;

	void inviteFriend(FriendDto friendDto) throws IOException;
}
