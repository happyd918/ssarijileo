package com.ssafy.ssarijileo.api.friend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.api.friend.client.FriendClient;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteEvent;
import com.ssafy.ssarijileo.api.friend.dto.FriendRequestEvent;
import com.ssafy.ssarijileo.api.friend.dto.FriendResponseDto;
import com.ssafy.ssarijileo.api.profile.entitiy.Profile;
import com.ssafy.ssarijileo.api.profile.repository.ProfileJpaRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.entity.Friend;
import com.ssafy.ssarijileo.api.friend.repository.FriendJpaRepository;
import com.ssafy.ssarijileo.api.friend.repository.FriendRepository;
import com.ssafy.ssarijileo.common.model.AlarmUser;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

	private final FriendJpaRepository friendJpaRepository;
	private final FriendRepository friendRepository;
	private final ProfileJpaRepository profileJpaRepository;

	private final FriendClient friendClient;

	@Override
	public List<FriendResponseDto> findAllFriend(String nickname) {
		return friendRepository.findAllFriend(nickname).orElseThrow(NotFoundException::new);
	}

	@Override
	public List<MyFriendDto> findFriendByNickname(String nickname) {
		return friendRepository.findFriendByNickname(nickname).orElseThrow(NotFoundException::new);
	}

	@Override
	public void requestFriend(FriendDto friendDto) {
		Profile fromProfile = profileJpaRepository.findByNickname(friendDto.getFromUserNickname()).orElseThrow(NotFoundException::new);
		Profile toProfile = profileJpaRepository.findByNickname(friendDto.getToUserNickname()).orElseThrow(NotFoundException::new);
		Friend friend = Friend.builder().friendDto(friendDto).fromProfile(fromProfile).toProfile(toProfile).build();
		friendJpaRepository.save(friend);

		System.out.println("friendId : " + friend.getFriendId());
		// 친구 요청 알림
		AlarmUser user = new AlarmUser(fromProfile.getProfileId(), toProfile.getProfileId());
		FriendRequestEvent friendRequestEvent = new FriendRequestEvent(user, fromProfile.getNickname(), friend.getFriendId());
		friendClient.requestFriend(friendRequestEvent);
	}

	@Override
	public void updateFriend(FriendUpdateDto friendUpdateDto) {
		Friend friend = friendJpaRepository.findById(friendUpdateDto.getFriendId()).orElseThrow(NotFoundException::new);
		friend.updateFriend(friendUpdateDto.getStatus());
	}

	@Override
	public void inviteFriend(FriendInviteDto friendInviteDto) {
		Profile fromProfile = profileJpaRepository.findByNickname(friendInviteDto.getFromUserNickname()).orElseThrow(NotFoundException::new);
		Profile toProfile = profileJpaRepository.findByNickname(friendInviteDto.getToUserNickname()).orElseThrow(NotFoundException::new);

		// 친구 초대 알림
		AlarmUser user = new AlarmUser(fromProfile.getProfileId(), toProfile.getProfileId());
		FriendInviteEvent friendInviteEvent = new FriendInviteEvent(user, fromProfile.getNickname(), friendInviteDto.getSessionId());
		friendClient.inviteFriend(friendInviteEvent);
	}
}
