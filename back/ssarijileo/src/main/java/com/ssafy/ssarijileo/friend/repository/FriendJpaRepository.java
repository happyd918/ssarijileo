package com.ssafy.ssarijileo.friend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.friend.entity.Friend;

@Repository
public interface FriendJpaRepository extends JpaRepository<Friend, Long> {
}
