package com.ssafy.ssarijileo.friend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.friend.dto.MyFriendDto;

public interface FriendRepository {

	Optional<List<MyFriendDto>> findFriendByUserId(String userId);
}
