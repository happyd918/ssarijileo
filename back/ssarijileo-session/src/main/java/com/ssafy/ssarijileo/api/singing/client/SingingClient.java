package com.ssafy.ssarijileo.api.singing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ssafy.ssarijileo.api.singing.dto.SingingDto;
import com.ssafy.ssarijileo.test.client.BaseResponseBody;

@FeignClient(name = "singing-client", url = "localhost:8080/api/v1/singing")
public interface SingingClient {

	ResponseEntity<? extends BaseResponseBody> insertSinging(@RequestHeader String userId, @RequestBody SingingDto singingDto);

}
