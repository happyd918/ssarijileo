package com.ssafy.ssarijileo.common.sse.controller;

import java.io.IOException;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.querydsl.codegen.utils.support.ClassUtils;
import com.ssafy.ssarijileo.common.sse.service.SseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sse")
public class SseController {

	private final SseService sseService;

	/**
	 * @title 로그인 한 사용자 SSE 연결
	 * @param userId
	 * @return
	 */
	@GetMapping("{userId}")
	public SseEmitter connection(@PathVariable String userId) {
		return sseService.connection(userId);
	}
}
