package com.ssafy.ssarijileo.api.room.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.room.dto.RoomDto;
import com.ssafy.ssarijileo.api.room.dto.RoomRequestDto;
import com.ssafy.ssarijileo.api.room.dto.RoomResponseDto;
import com.ssafy.ssarijileo.api.room.service.RoomService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/room", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoomController {

	private final RoomService roomService;

	@GetMapping
	List<RoomResponseDto> findAllRoom() {
		return roomService.findAllRoom();
	}

	@PostMapping
	void createRoom(@RequestBody RoomDto roomDto) {
		roomService.createRoom(roomDto);
	}

	@PutMapping("/in")
	void enterRoom(@RequestBody RoomRequestDto roomRequestDto) {
		roomService.enterRoom(roomRequestDto);
	}

	@PutMapping("/out")
	void leaveRoom(@RequestBody RoomRequestDto roomRequestDto) {
		roomService.leaveRoom(roomRequestDto);
	}

	@DeleteMapping("/{sessionId}")
	void deleteRoom(@PathVariable String sessionId) {
		roomService.deleteRoom(sessionId);
	}
}
