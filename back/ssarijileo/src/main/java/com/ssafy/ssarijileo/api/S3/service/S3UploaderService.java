package com.ssafy.ssarijileo.api.S3.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3UploaderService {

	@Value("${spring.environment}")
	private String environment;

	@Value("${spring.file-dir}")
	private String rootDir;
	private String fileDir;

	private final AmazonS3Client amazonS3Client;

	private void init(){
		if(environment.equals("local")){
			this.fileDir = System.getProperty("user.dir") + this.rootDir;
		}
		else if(envi)
	}

}
