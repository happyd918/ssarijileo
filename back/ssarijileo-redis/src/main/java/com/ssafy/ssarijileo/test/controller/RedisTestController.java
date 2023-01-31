package com.ssafy.ssarijileo.test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.test.service.RedisTestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class RedisTestController {

	private final RedisTestService redisTestService;

	@GetMapping("/getSessionId")
	public String getSessionId(HttpSession session) {
		return session.getId();
	}

	@PostMapping("/getRedisStringValue")
	public void getRedisStringValue(String key) {
		redisTestService.getRedisStringValue(key);
	}

	@PostMapping("/setRedisStringValue")
	public void setRedisStringValue(String key, String value) {
		redisTestService.setRedisStringValue(key, value);
	}
}
