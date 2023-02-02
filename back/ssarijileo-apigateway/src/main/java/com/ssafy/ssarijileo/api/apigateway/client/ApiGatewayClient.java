package com.ssafy.ssarijileo.api.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "ApiGatewayClient", url = "localhost:8080/")
public class ApiGatewayClient {

}
