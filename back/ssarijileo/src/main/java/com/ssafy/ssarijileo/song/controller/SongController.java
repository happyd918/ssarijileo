package com.ssafy.ssarijileo.song.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.song.dto.SongDto;
import com.ssafy.ssarijileo.song.service.SongService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/song")
@RequiredArgsConstructor // private final 변수에 의존성 주입 (@Autowired 대체)
public class SongController {

	private final SongService songService;

	/**
	 * 노래 목록 조회
	 * @return
	 */
	@ApiOperation(
		value = "노래 목록 조회",
		notes = "노래 전체 목록을 조회한다."
	)
	@GetMapping
	public List<SongDto> findAllSong() {
		return songService.findAllSong();
	}

	/**
	 * 노래 정보 조회
	 * @param id
	 * @return
	 */
	@ApiOperation(
		value = "노래 정보 조회",
		notes = "노래 ID를 통해 노래 정보를 조회한다."
	)
	@ApiImplicitParam(
		name = "id",
		value = "노래 PK"
	)
	@GetMapping("{id}")
	public SongDto findSongById(@PathVariable Long id) {
		return songService.findSongById(id);
	}
}
