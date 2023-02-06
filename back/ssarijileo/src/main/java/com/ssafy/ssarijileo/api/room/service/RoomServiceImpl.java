package com.ssafy.ssarijileo.api.room.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.room.dto.RoomDto;
import com.ssafy.ssarijileo.api.room.dto.RoomRequestDto;
import com.ssafy.ssarijileo.api.room.dto.RoomResponseDto;
import com.ssafy.ssarijileo.api.room.repository.RoomRedisRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomServiceImpl implements RoomService{

	private final RoomRedisRepository roomRedisRepository;

	@Override
	public List<RoomResponseDto> findAllRoom() {
		return null;
	}

	@Override
	public void createRoom(RoomDto roomDto) {

	}

	@Override
	public void enterRoom(RoomRequestDto roomEntryDto) {

	}

	@Override
	public void leaveRoom(RoomRequestDto roomEntryDto) {

	}
}
