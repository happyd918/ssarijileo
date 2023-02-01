package com.ssafy.ssarijileo.api.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssarijileo.api.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.api.friend.entity.QFriend;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository{

	private final JPAQueryFactory jpaQueryFactory;

	QFriend friend = QFriend.friend;

	@Override
	public Optional<List<MyFriendDto>> findFriendByUserId(String userId) {
		List<MyFriendDto> friendList = jpaQueryFactory
			.select(Projections.fields(MyFriendDto.class,
				friend.friendId,
				/*
					friend.sendingUserId
					.when(userId).then(friend.receivingUserId)
					.otherwise(friend.sendingUserId)
					.as("userId"),
				 */
				new CaseBuilder()
					.when(friend.sendingUserId.contains(userId)).then(friend.receivingUserId)
					.otherwise(friend.sendingUserId)
					.as("userId"),
				friend.status))
			.from(friend)
			.where(
				friend.status.ne('X')
					.and(
						/*
						(friend.sendingUserId.eq(userId)
							.and(friend.status.eq('A')))
							.or(friend.receivingUserId.eq(userId))
						 */
						(friend.sendingUserId.contains(userId)
							.and(friend.status.eq('A')))
							.or(friend.receivingUserId.contains(userId))
					)
			)
			.orderBy(friend.status.desc())
			.fetch();
		if(friendList == null) return Optional.empty();
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
