package com.ssafy.ssarijileo.api.user.client;

import com.ssafy.ssarijileo.api.user.dto.ProfileDto;
import com.ssafy.ssarijileo.common.model.BaseResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-profile-client", url = "192.168.49.2:31000/api/v1/profile")
public interface UserProfileClient {
    @PostMapping(produces = "application/json")
    ResponseEntity<? extends BaseResponseBody> insertProfile(@RequestBody ProfileDto profileDto);

    @PostMapping(value = "/image", produces = "application/json")
    ResponseEntity<? extends BaseResponseBody> updateImage(@RequestBody ProfileDto profileDto);
}
