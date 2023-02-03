package com.ssafy.ssarijileo.api.song.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.song.dto.SongDto;
import com.ssafy.ssarijileo.api.song.entity.Song;
import com.ssafy.ssarijileo.api.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

	private final SongJpaRepository songJpaRepository;

	@Override
	public List<SongDto> findAllSong() {
		return songJpaRepository.findAll().stream().map(Song::toDto).collect(Collectors.toList());
	}

	@Override
	public SongDto findSongById(Long songId) {
		return songJpaRepository.findById(songId).orElseThrow(NotFoundException::new).toDto();
	}
}
