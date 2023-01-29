package com.ssafy.ssarijileo.singing.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.singing.dto.SingingDto;
import com.ssafy.ssarijileo.singing.service.SingingService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/singing")
@RequiredArgsConstructor
public class SingingController {

	private final SingingService singingService;

	@ApiOperation(
		value = "사용자 노래 정보 저장",
		notes = "사용자가 노래 부른 정보를 저장한다."
	)
	@PostMapping
	public void insertSinging(@RequestBody SingingDto singingDto) {
		singingService.insertSinging(singingDto);
	}
}
