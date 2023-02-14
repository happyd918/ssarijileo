package com.ssafy.ssarijileo.api.song.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		// 캐시에 유저 애창곡 정보가 없을 경우 DB에서 받아옴
		if (!favoriteSongService.hasKey(userId)) {
			String[] favoriteSong = favoriteSongJpaRepository.findLatestSongIdByUserId(userId)[0].split(" ");
			for (String songId : favoriteSong) {
				favoriteSongService.subscribe(userId, Long.parseLong(songId));
			}
		}
		return songRepository.findFavoriteSongByUserId(userId).orElseThrow(NotFoundException::new);
	}

	@Override
	public String setFavoriteSong(FavoriteSongDto favoriteSongDto) {
		switch(favoriteSongDto.getIsLike()) {
			case "Y" : favoriteSongService.subscribe(favoriteSongDto.getUserId(), favoriteSongDto.getSongId()); break;
			case "N" : favoriteSongService.unsubscribe(favoriteSongDto.getUserId(), favoriteSongDto.getSongId()); break;
			default : break;
		}
		return favoriteSongService.getUsersFavoriteSong(favoriteSongDto.getUserId()).toString();
	}

	// 매일 3시 저장
	@Override
	@Scheduled(cron = "0 0 3 * * *")
	public void saveFavoriteSong() {
		Set<String> keys = favoriteSongService.getKeys();

		for (String key : keys) {
			StringBuffer sb = new StringBuffer();
			String userId = key.split(":")[1];

			Set<String> values = favoriteSongService.getUsersFavoriteSong(key);
			for (String value : values) {
				sb.append(value).append(" ");
			}
			FavoriteSong favoriteSong = FavoriteSong.builder().userId(userId).songId(sb.toString()).build();
			favoriteSongJpaRepository.save(favoriteSong);
		}
	}
}
