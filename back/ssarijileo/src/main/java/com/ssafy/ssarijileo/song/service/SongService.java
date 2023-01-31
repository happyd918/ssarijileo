package com.ssafy.ssarijileo.song.service;

import java.util.List;

import com.ssafy.ssarijileo.song.dto.SongDto;

public interface SongService {

	List<SongDto> findAllSong();

	SongDto findSongById(Long id);
}
