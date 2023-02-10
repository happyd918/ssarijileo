package com.ssafy.ssarijileo.api.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteDto;

@FeignClient(name = "friend-client", url = "localhost:8060/api/v1/sse")
public interface FriendClient {

	@PostMapping
	void requestFriend(@RequestBody FriendDto friendDto);

	@PostMapping("/invite")
	void inviteFriend(@RequestBody FriendInviteDto friendInviteDto);
}
