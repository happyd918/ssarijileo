package com.ssafy.ssarijileo.api.singing.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.api.singing.dto.SingingDto;
import com.ssafy.ssarijileo.api.song.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Singing {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long singingId;

	// 사용자PK
	private String userId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	private Song song;

	// 모드(N:일반,P:퍼펙트스코어,O:가사순서맞추기,R:이어부르기)
	private char mode;

	// 점수
	private int score;

	// 총부른시간
	private String totalSingingTime;

	// Dto to Entity
	@Builder
	public Singing(SingingDto singingDto, Song song) {
		this.singingId = singingDto.getSingingId();
		this.userId = singingDto.getUserId();
		this.song = song;
		this.mode = singingDto.getMode();
		this.score = singingDto.getScore();
		this.totalSingingTime = singingDto.getTotalSingingTime();
	}
}
