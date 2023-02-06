package com.ssafy.ssarijileo.test.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "friend-client", url = "localhost:8080/friend")
public interface FriendClient {

	@GetMapping("/test")
	ResponseEntity<? extends BaseResponseBody> testFriend2();
}
