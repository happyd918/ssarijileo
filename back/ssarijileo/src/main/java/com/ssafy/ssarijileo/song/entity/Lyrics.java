package com.ssafy.ssarijileo.song.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.song.dto.LyricsDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Lyrics {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long lyricsId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	Song song;

	// 한소절
	String verse;

	// 상태(N:기본,O:가사순서맞추기,H:하이라이트)
	char status;

	// Entity to Dto
	public LyricsDto toDto() {
		return new LyricsDto(lyricsId, song.getSongId(), verse, status);
	}
}
