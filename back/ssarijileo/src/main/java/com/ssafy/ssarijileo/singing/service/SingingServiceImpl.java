package com.ssafy.ssarijileo.singing.service;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.exception.NotFoundException;
import com.ssafy.ssarijileo.singing.dto.SingingDto;
import com.ssafy.ssarijileo.singing.entity.Singing;
import com.ssafy.ssarijileo.singing.repository.SingingJpaRepository;
import com.ssafy.ssarijileo.song.entity.Song;
import com.ssafy.ssarijileo.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SingingServiceImpl implements SingingService{

	private final SingingJpaRepository singingJpaRepository;
	private final SongJpaRepository songJpaRepository;

	@Override
	public void insertSinging(SingingDto singingDto) {
		Song song = songJpaRepository.findById(singingDto.getSongId()).orElseThrow(NotFoundException::new);
		Singing singing = Singing.builder().singingDto(singingDto).song(song).build();
		singingJpaRepository.save(singing);
	}
}
