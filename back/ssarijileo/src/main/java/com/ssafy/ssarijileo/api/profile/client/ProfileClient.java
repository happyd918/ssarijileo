package com.ssafy.ssarijileo.api.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@FeignClient(name = "profile-client", url = "localhost:8060/api/v1/sse")
public interface ProfileClient {

	@GetMapping(value = "/{userId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	SseEmitter connection(@PathVariable String userId);
}
