package com.ssafy.ssarijileo.api.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@FeignClient(name = "profile-client", url = "notification:8060/api/v1/sse")
public interface ProfileClient {

	@GetMapping(value = "/{userId}", produces = "text/event-stream")
	SseEmitter connection(@PathVariable String userId);
}
