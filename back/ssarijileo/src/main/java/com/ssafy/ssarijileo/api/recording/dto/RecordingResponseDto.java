package com.ssafy.ssarijileo.api.recording.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordingResponseDto {

	// PK (AUTO_INCREMENT)
	Long recordingId;

	// 제목
	String title;

	// 가수
	String singer;

	// 녹화파일
	String file;

	// 녹화일시
	String registerDate;
}
