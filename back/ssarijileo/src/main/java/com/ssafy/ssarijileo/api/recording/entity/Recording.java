package com.ssafy.ssarijileo.api.recording.entity;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.api.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.api.song.entity.Song;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Recording {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long recordingId;

	// 사용자PK
	private UUID userId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	private Song song;

	// 녹화파일
	private String file;

	// 녹화일시
	private String registerDate;

	// Dto to Entity
	@Builder
	public Recording(RecordingDto recordingDto, Song song) {
		this.recordingId = recordingDto.getRecordingId();
		this.userId = UUID.fromString(recordingDto.getUserId());
		this.song = song;
		this.file = recordingDto.getFile();
		this.registerDate = recordingDto.getRegisterDate();
	}

	// Entity to Dto
	public RecordingDto toDto(){
		return new RecordingDto(recordingId, String.valueOf(userId), song.getSongId(), file, registerDate);
	}
}
