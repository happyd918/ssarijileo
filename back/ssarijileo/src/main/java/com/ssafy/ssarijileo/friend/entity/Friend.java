package com.ssafy.ssarijileo.friend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.ssarijileo.friend.dto.FriendDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long friendId;

	// 보낸 사용자PK
	String sendingUserId;

	// 받는 사용자PK
	String receivingUserId;

	// 상태(W:대기,A:수락,X:취소)
	char status;

	// Dto to Entity
	@Builder
	public Friend(FriendDto friendDto) {
		this.friendId = friendDto.getFriendId();
		this.sendingUserId = friendDto.getSendingUserId();
		this.receivingUserId = friendDto.getReceivingUserId();
		this.status = friendDto.getStatus();
	}

	// Entity to Dto
	public FriendDto toDto() {
		return new FriendDto(friendId, sendingUserId, receivingUserId, status);
	}

	public void updateFriend(char status) {
		this.status = status;
	}
}
