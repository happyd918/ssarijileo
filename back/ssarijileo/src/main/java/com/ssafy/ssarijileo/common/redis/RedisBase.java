package com.ssafy.ssarijileo.common.redis;

import java.time.Duration;
import java.util.Optional;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisBase {

	private final RedisTemplate redisTemplate;

	private final ObjectMapper objectMapper;

	public <T> Optional<T> get(String key, Class<T> classType) {
		Object result = redisTemplate.opsForValue().get(key);
		return result == null ? Optional.empty()
			: Optional.of(objectMapper.convertValue(result, classType));
	}

	public <T> void set(String key, T value, Duration expireTime) {
		redisTemplate.opsForValue().set(key, value, expireTime);
	}

	public void remove(String key) {
		redisTemplate.delete(key);
	}
}
