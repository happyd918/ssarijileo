package com.ssafy.ssarijileo.api.friend.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.entity.QFriend;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository {

	private final JPAQueryFactory jpaQueryFactory;

	QFriend friend = QFriend.friend;

	@Override
	public Optional<List<MyFriendDto>> findFriendByUserId(String userId) {
		List<MyFriendDto> friendList = jpaQueryFactory
			.select(Projections.fields(MyFriendDto.class,
				friend.friendId,
				friend.fromUserId
					.when(UUID.fromString(userId)).then(friend.toUserId)
					.otherwise(friend.fromUserId)
					.as("userId"),
				friend.status))
			.from(friend)
			.where(
				friend.status.ne('X')
					.and(
						(friend.fromUserId.eq(UUID.fromString(userId))
							.and(friend.status.eq('A')))
							.or(friend.toUserId.eq(UUID.fromString(userId)))
					)
			)
			.orderBy(friend.status.desc())
			.fetch();
		if (friendList == null)
			return Optional.empty();
		return Optional.ofNullable(friendList);
	}

	// 종목 필터링(복수) 검색
	// private BooleanBuilder checkConditions(List<String> conditions){
	// 	if(conditions == null) return null;
	//
	// 	BooleanBuilder booleanBuilder = new BooleanBuilder();
	//
	// 	for (String condition : conditions){
	// 		booleanBuilder.or(friend.exercise.eq(condition));
	// 	}
	//
	// 	return booleanBuilder;
	// }
}
