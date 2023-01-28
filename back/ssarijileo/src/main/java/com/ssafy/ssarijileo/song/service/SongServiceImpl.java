package com.ssafy.ssarijileo.song.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.exception.NotFoundException;
import com.ssafy.ssarijileo.song.dto.SongDto;
import com.ssafy.ssarijileo.song.entity.Song;
import com.ssafy.ssarijileo.song.repository.SongRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

	private final SongRepository songRepository;

	@Override
	public List<SongDto> findAllSong() {
		return songRepository.findAll().stream().map(Song::toDto).collect(Collectors.toList());
	}

	@Override
	public SongDto findSongById(Long id) {
		return songRepository.findById(id).orElseThrow(NotFoundException::new).toDto();
	}
}
