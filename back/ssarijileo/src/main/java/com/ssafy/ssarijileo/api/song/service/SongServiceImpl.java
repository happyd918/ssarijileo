package com.ssafy.ssarijileo.api.song.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.song.dto.FavoriteSongDto;
import com.ssafy.ssarijileo.api.song.dto.SongDto;
import com.ssafy.ssarijileo.api.song.entity.FavoriteSong;
import com.ssafy.ssarijileo.api.song.repository.FavoriteSongJpaRepository;
import com.ssafy.ssarijileo.api.song.repository.SongRepository;
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
	private final SongRepository songRepository;
	private final FavoriteSongJpaRepository favoriteSongJpaRepository;
	private final FavoriteSongService favoriteSongService;

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
		return songRepository.findFavoriteSongByUserId(userId).orElseThrow(NotFoundException::new);
	}

	@Override
	public void setFavoriteSong(FavoriteSongDto favoriteSongDto) {
		Song song = songJpaRepository.findById(favoriteSongDto.getSongId()).orElseThrow(NotFoundException::new);
		FavoriteSong favoriteSong = FavoriteSong.builder().favoriteSongDto(favoriteSongDto).song(song).build();
		favoriteSongJpaRepository.save(favoriteSong);

		// 캐시
		String userId = favoriteSongDto.getUserId();
		Long songId = favoriteSongDto.getSongId();
		String isLike = favoriteSongDto.getIsLike();
		boolean subscribed = favoriteSongService.hasSubscribed(userId, songId);

		// 교차 검증
		if (isLike.equals("Y") && !subscribed) {
			favoriteSongService.subscribe(userId, songId);
		} else if (isLike.equals("N") && subscribed) {
			favoriteSongService.unsubscribe(userId, songId);
		}
	}
}
