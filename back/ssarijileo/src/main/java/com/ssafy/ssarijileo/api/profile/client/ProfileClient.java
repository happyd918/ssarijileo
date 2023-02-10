package com.ssafy.ssarijileo.api.profile.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-client", url = "localhost:8060/api/v1/sse")
public interface ProfileClient {

	@GetMapping("/{userId}")
	void connection(@PathVariable String userId);
}
