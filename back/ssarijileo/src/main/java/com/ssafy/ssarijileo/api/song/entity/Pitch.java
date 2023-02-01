package com.ssafy.ssarijileo.api.song.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.api.song.dto.PitchDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pitch {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long pitchId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	Song song;

	// 시간
	Double time;

	// 음표
	String note;

	// Entity to Dto
	public PitchDto toDto() {
		return new PitchDto(pitchId, song.getSongId(), time, note);
	}
}
