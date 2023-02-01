package com.ssafy.ssarijileo.api.song.service;

import java.util.List;

import com.ssafy.ssarijileo.api.song.dto.SongDto;

public interface SongService {

	List<SongDto> findAllSong();

	SongDto findSongById(Long id);
}
