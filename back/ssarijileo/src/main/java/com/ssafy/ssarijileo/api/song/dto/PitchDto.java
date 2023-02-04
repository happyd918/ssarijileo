package com.ssafy.ssarijileo.api.song.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PitchDto {

	// PK (AUTO_INCREMENT)
	Long pitchId;

	// 시간
	Double time;

	// 음표
	String note;
}
