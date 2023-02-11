package com.ssafy.ssarijileo.api.friend.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.friend.client.FriendClient;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteDto;
import com.ssafy.ssarijileo.api.profile.entitiy.Profile;
import com.ssafy.ssarijileo.api.profile.repository.ProfileJpaRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.friend.dto.FriendUpdateDto;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.entity.Friend;
import com.ssafy.ssarijileo.api.friend.repository.FriendJpaRepository;
import com.ssafy.ssarijileo.api.friend.repository.FriendRepository;

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
	public List<MyFriendDto> findFriendByNickname(String nickname) {
		return friendRepository.findFriendByNickname(nickname).orElseThrow(NotFoundException::new);
	}

	@Override
	public void requestFriend(FriendDto friendDto) {
		Profile fromProfile = profileJpaRepository.findByNickname(friendDto.getFromUserNickname()).orElseThrow(NotFoundException::new);
		Profile toProfile = profileJpaRepository.findByNickname(friendDto.getToUserNickname()).orElseThrow(NotFoundException::new);
		Friend friend = Friend.builder().friendDto(friendDto).fromProfile(fromProfile).toProfile(toProfile).build();
		friendJpaRepository.save(friend);

		// 친구 요청 알림
		friendClient.requestFriend(friendDto);

		// Profile profile = friendRequestEvent.findById(friend.getFromUserId()).orElseThrow(NotFoundException::new);

		// 친구 요청 알림
		// FriendRequestEvent friendRequestEvent = FriendRequestEvent.builder()
		// 	.user(AlarmUser.builder().fromUserId(String.valueOf(friend.getFromUserId())).toUserId(String.valueOf(friend.getToUserId())).build())
		// 	.from_user_nickname(profile.getNickname())
		// 	.friendId(friend.getFriendId())
		// 	.build();
		// friendRequestProducer.send(friendRequestEvent);
	}

	@Override
	public void updateFriend(FriendUpdateDto friendUpdateDto) {
		Friend friend = friendJpaRepository.findById(friendUpdateDto.getFriendId()).orElseThrow(NotFoundException::new);
		friend.updateFriend(friendUpdateDto.getStatus());
	}

	@Override
	public void inviteFriend(FriendInviteDto friendInviteDto) {
		// 친구 초대 알림
		friendClient.inviteFriend(friendInviteDto);

		// Profile profile = profileJpaRepository.findById(friendInviteDto.getFromUserId()).orElseThrow(NotFoundException::new);

		// 친구 초대 알림
		// FriendInviteEvent friendInviteEvent = FriendInviteEvent.builder()
		// 	.user(AlarmUser.builder().fromUserId(friendInviteDto.getFromUserId()).toUserId(friendInviteDto.getToUserId()).build())
		// 	.from_user_nickname(profile.getNickname())
		// 	.link(friendInviteDto.getLink())
		// 	.build();
		// friendInviteProducer.send(friendInviteEvent);
	}
}
