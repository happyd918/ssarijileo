package com.ssafy.ssarijileo.api.S3.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
public class S3UploadController {
	private final S3UploadService s3UploadService;

	@GetMapping("/song")
	public String song() {
		return "image-upload";
	}

	@GetMapping("/video")
	public String video() {
		return "video-upload";
	}

	@PostMapping("/song")
	public String imageUpload(MultipartFile multipartFile) throws IOException{
		return s3UploadService.upload(multipartFile, "1-source", "image");
	}

	@PostMapping("/video")
	public String videoUpload(MultipartFile multipartFile) throws IOException{
		return s3UploaderService.upload(multipartFile, "1-source", "video")
	}


}
