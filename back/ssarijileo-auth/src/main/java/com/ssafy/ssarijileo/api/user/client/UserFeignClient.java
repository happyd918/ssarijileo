package com.ssafy.ssarijileo.api.user.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "userFeignClient", url = "localhost:8080/friend")
public interface UserFeignClient {
}