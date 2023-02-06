package com.ssafy.ssarijileo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EnableRedisHttpSession
public class SsarijileoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsarijileoApplication.class, args);
	}

}
