package com.ssafy.ssarijileo.api.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.entity.QFriend;
import com.ssafy.ssarijileo.api.profile.entitiy.QProfile;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {

	private final JPAQueryFactory jpaQueryFactory;

	QFriend friend = QFriend.friend;
	QProfile profile = QProfile.profile;

	@Override
	public Optional<List<MyFriendDto>> findFriendByUserId(String userId) {
		List<MyFriendDto> friendList = jpaQueryFactory
			.select(Projections.fields(MyFriendDto.class,
				friend.friendId,
				friend.fromProfile.profileId
					.when(userId).then(friend.toProfile.profileId)
					.otherwise(friend.fromProfile.profileId)
					.as("userId"),
				profile.nickname,
				profile.image,
				friend.status))
			.from(friend)
			.leftJoin(profile)
			.on(friend.fromProfile.profileId
				.when(userId).then(friend.toProfile.profileId)
				.otherwise(friend.fromProfile.profileId).eq(profile.profileId))
			.where(
				friend.status.ne("X")
					.and(
						(friend.fromProfile.profileId.eq(userId)
							.and(friend.status.eq("A")))
							.or(friend.toProfile.profileId.eq(userId))
					)
			)
			.orderBy(friend.status.desc())
			.fetch();

		if (friendList == null)
			return Optional.empty();
		return Optional.ofNullable(friendList);
	}
}
