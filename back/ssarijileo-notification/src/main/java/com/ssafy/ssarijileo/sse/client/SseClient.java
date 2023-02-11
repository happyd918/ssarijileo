package com.ssafy.ssarijileo.sse.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@FeignClient(name = "sse-client", url = "192.168.49.2:31007/api/v1/profile")
public interface SseClient {

	@GetMapping("/{nickname}")
	String findIdByNickname(@PathVariable String nickname);
}
