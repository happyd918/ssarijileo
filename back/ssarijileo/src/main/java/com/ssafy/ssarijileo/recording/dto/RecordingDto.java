package com.ssafy.ssarijileo.recording.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordingDto {

	// PK (AUTO_INCREMENT)
	Long recordingId;

	// 사용자PK
	String userId;

	// 노래PK
	Long songId;

	// 녹화파일
	String file;

	// 녹화일시
	String registerDate;
}
