package com.ssafy.ssarijileo.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PitchDto {

	// PK (AUTO_INCREMENT)
	Long pitchId;

	// 노래PK
	Long songId;

	// 시간
	Double time;

	// 음표
	String note;
}
