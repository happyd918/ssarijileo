package com.ssafy.ssarijileo.api.singingcontest.dto;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.api.recording.entity.Recording;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SingingContestResponseDto {

	// PK (AUTO_INCREMENT)
	private Long singingContestId;

	// 사용자 닉네임
	String nickname;

	// 제목
	String title;

	// 가수
	String singer;

	// 녹화 파일
	String file;

	// 등록 일시
	String registerDate;
}
