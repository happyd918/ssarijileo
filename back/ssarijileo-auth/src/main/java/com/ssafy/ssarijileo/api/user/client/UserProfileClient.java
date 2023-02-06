package com.ssafy.ssarijileo.api.user.client;

import com.ssafy.ssarijileo.api.user.dto.ProfileDto;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "userProfileClient", url = "localhost:8080/api/v1/profile")
public interface UserProfileClient {
    @PostMapping(produces = "application/json")
    ResponseEntity<? extends BaseResponseBody> insertSinging(@RequestBody ProfileDto profileDto);
}
