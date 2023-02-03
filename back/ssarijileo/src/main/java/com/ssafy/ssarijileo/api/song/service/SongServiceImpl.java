package com.ssafy.ssarijileo.api.song.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.song.dto.SongDto;
import com.ssafy.ssarijileo.api.song.entity.FavoriteSong;
import com.ssafy.ssarijileo.api.song.repository.FavoriteSongJpaRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.song.dto.SongDetailDto;
import com.ssafy.ssarijileo.api.song.entity.Song;
import com.ssafy.ssarijileo.api.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

	private final SongJpaRepository songJpaRepository;
	private final FavoriteSongJpaRepository favoriteSongJpaRepository;

	@Override
	public List<SongDto> findAllSong() {
		return songJpaRepository.findAll().stream().map(Song::toDto).collect(Collectors.toList());
	}

	@Override
	public List<SongDetailDto> findAllSongDetail() {
		return songJpaRepository.findAll().stream().map(Song::toDetailDto).collect(Collectors.toList());
	}

	@Override
	public SongDetailDto findSongDetailById(Long songId) {
		return songJpaRepository.findById(songId).orElseThrow(NotFoundException::new).toDetailDto();
	}

	@Override
	public List<SongDto> findSongByUserId(String userId) {
		return favoriteSongJpaRepository.findByUserId(userId).orElseThrow(NotFoundException::new)
			.stream().map(FavoriteSong::toDto).collect(Collectors.toList());
	}
}
