package com.ssafy.ssarijileo.api.room.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.ssarijileo.api.room.dto.RoomDto;
import com.ssafy.ssarijileo.api.room.dto.RoomRequestDto;
import com.ssafy.ssarijileo.api.room.dto.RoomResponseDto;

@FeignClient(name = "room-client", url = "localhost:8070/api/v1/room")
public interface RoomClient {

	@GetMapping
	List<RoomResponseDto> findAllRoom();

	@PostMapping
	void createRoom(@RequestBody RoomDto roomDto);

	@PutMapping
	void enterRoom(@RequestBody RoomRequestDto roomRequestDto);

	@DeleteMapping
	void leaveRoom(@RequestBody RoomRequestDto roomRequestDto);
}
