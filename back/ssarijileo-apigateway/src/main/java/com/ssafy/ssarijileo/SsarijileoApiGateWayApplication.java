package com.ssafy.ssarijileo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SsarijileoApiGateWayApplication {

	public static void main(String[] args) {
		SpringApplication.run(SsarijileoApiGateWayApplication.class, args);
	}

}