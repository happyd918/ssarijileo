package com.ssafy.ssarijileo.api.friend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Friend {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long friendId;

	// 보낸 사용자PK
	private String fromUserId;

	// 받는 사용자PK
	private String toUserId;

	// 상태(W:대기,A:수락,X:취소)
	private String status;

	// Dto to Entity
	@Builder
	public Friend(FriendDto friendDto) {
		this.friendId = friendDto.getFriendId();
		this.fromUserId = friendDto.getFromUserId();
		this.toUserId = friendDto.getToUserId();
		this.status = friendDto.getStatus();
	}

	// Entity to Dto
	public FriendDto toDto() {
		return new FriendDto(friendId, fromUserId, toUserId, status);
	}

	public void updateFriend(String status) {
		this.status = status;
	}
}
