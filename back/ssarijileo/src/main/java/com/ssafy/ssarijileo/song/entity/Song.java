package com.ssafy.ssarijileo.song.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.ssafy.ssarijileo.song.dto.LyricsDto;
import com.ssafy.ssarijileo.song.dto.PitchDto;
import com.ssafy.ssarijileo.song.dto.SongDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Song {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;

	// 제목
	private String title;

	// 가수
	private String singer;

	// 앨범명
	private String album;

	// 시간
	private String time;

	// 앨범이미지
	private String image;

	// 발매일자
	private String releaseDate;

	// 가사 목록
	@OneToMany(mappedBy = "song")
	private List<Lyrics> lyricsList = new ArrayList<>();

	// 음정 목록
	@OneToMany(mappedBy = "song")
	private List<Pitch> pitchList = new ArrayList<>();

	// Entity to Dto
	public SongDto toDto() {
		List<LyricsDto> lyricsDtoList = lyricsList.stream().map(Lyrics::toDto).collect(Collectors.toList());
		List<PitchDto> pitchDtoList = pitchList.stream().map(Pitch::toDto).collect(Collectors.toList());
		return new SongDto(songId, title, singer, album, time, image, releaseDate, lyricsDtoList, pitchDtoList);
	}
}
