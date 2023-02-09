package com.ssafy.ssarijileo.api.reservation.repository;

import java.time.Duration;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.api.room.dto.RoomDto;
import com.ssafy.ssarijileo.common.redis.RedisBase;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ReservationRepository {

	private final RedisBase redisBase;

	private static final Duration TTL = Duration.ofDays(1);

	private String category = "reservation_";

	private Class classType = ReservationDto.class;

	public Optional<RoomDto> get(String key) {
		return redisBase.get(getKey(key), RoomDto.class);
	}

	public void set(String key, RoomDto value) {
		redisBase.set(getKey(key), value, TTL);
	}

	public void remove(String key) {
		redisBase.remove(getKey(key));
	}

	public String getKey(String sessionId) {
		return category + sessionId;
	}
}
