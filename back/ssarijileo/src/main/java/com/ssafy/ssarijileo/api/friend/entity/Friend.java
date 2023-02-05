package com.ssafy.ssarijileo.api.friend.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.profile.entitiy.Profile;

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
	@ManyToOne
	@JoinColumn(name = "from_user_id")
	private Profile fromProfile;

	// 받는 사용자PK
	@ManyToOne
	@JoinColumn(name = "to_user_id")
	private Profile toProfile;

	// 상태(W:대기,A:수락,X:취소)
	private String status;

	// Dto to Entity
	@Builder
	public Friend(FriendDto friendDto, Profile fromProfile, Profile toProfile) {
		this.friendId = friendDto.getFriendId();
		this.fromProfile = fromProfile;
		this.toProfile = toProfile;
		this.status = friendDto.getStatus();
	}

	// Entity to Dto
	public FriendDto toDto() {
		return new FriendDto(friendId, fromProfile.getProfileId(), toProfile.getProfileId(), status);
	}

	public void updateFriend(String status) {
		this.status = status;
	}
}
