package com.ssafy.ssarijileo.api.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LyricsDto {

	// PK (AUTO_INCREMENT)
	Long lyricsId;

	// 노래PK
	Long songId;

	// 한소절
	String verse;

	// 상태(N:기본,O:가사순서맞추기,H:하이라이트)
	char status;
}
