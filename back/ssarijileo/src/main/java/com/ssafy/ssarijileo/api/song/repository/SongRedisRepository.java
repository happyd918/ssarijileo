package com.ssafy.ssarijileo.api.song.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.api.friend.dto.FriendDto;
import com.ssafy.ssarijileo.api.song.dto.SongDetailDto;
import com.ssafy.ssarijileo.common.redis.RedisBase;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SongRedisRepository {

	private final RedisBase redisBase;

	private static final Duration TTL = Duration.ofDays(1);

	private String category = "song_";

	private Class classType = SongDetailDto.class;

	public Optional<FriendDto> get(String key) {
		return redisBase.get(getKey(key), classType);
	}

	public void set(String key, FriendDto value) {
		redisBase.set(getKey(key), value, TTL);
	}

	public void remove(String key) {
		redisBase.remove(getKey(key));
	}

	public String getKey(String userId) {
		return category + userId;
	}
}