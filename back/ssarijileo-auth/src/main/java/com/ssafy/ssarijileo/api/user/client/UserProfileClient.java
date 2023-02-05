package com.ssafy.ssarijileo.api.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userProfileClient", url = "localhost:8080/api/v1/profile")
public interface UserProfileClient {

}
