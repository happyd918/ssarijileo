package com.ssafy.ssarijileo.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.ssarijileo.friend.dto.MyFriendDto;
import com.ssafy.ssarijileo.friend.entity.QFriend;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class FriendRepositoryImpl implements FriendRepository{

	private final JPAQueryFactory jpaQueryFactory;

	QFriend friend = QFriend.friend;

	@Override
	public Optional<List<MyFriendDto>> findFriendByUserId(String userId) {
		System.out.println("in repo : "+convertStringToBinary(userId));
		userId = convertStringToBinary(userId);
		List<MyFriendDto> friendList = jpaQueryFactory
			.select(Projections.fields(MyFriendDto.class,
				friend.friendId,
				getUserId(userId).as("userId"),
				friend.status))
			.from(friend)
			.where(
				friend.status.ne('X')
					.and(
						(friend.sendingUserId.eq(userId)
							.and(friend.status.eq('A')))
							.or(friend.receivingUserId.eq(userId))
					)
			)
			.orderBy(friend.status.desc())
			.fetch();
		if(friendList == null) return Optional.empty();
		return Optional.ofNullable(friendList);

	}

	private StringPath getUserId(String userId){
		if(friend.sendingUserId.equals(userId)) return friend.receivingUserId;
		return friend.sendingUserId;
	}

	private static String convertStringToBinary(String input) {

		StringBuilder result = new StringBuilder();
		char[] chars = input.toCharArray();
		for (char aChar : chars) {
			result.append(
				String.format("%16s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
					.replaceAll(" ", "0")                         // zero pads
			);
		}
		return result.toString();

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
