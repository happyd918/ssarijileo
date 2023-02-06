package com.ssafy.ssarijileo.api.song.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ssafy.ssarijileo.api.song.dto.FavoriteSongDto;
import com.ssafy.ssarijileo.api.song.dto.SongDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteSong {

	// PK (AUTO_INCREMENT)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long favoriteSongId;

	// 사용자PK
	private String userId;

	// 노래PK
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "song_id")
	private Song song;

	// 좋아요여부(Y:좋아요,N:좋아요취소)
	private String isLike;

	// Dto to Entity
	@Builder
	public FavoriteSong(FavoriteSongDto favoriteSongDto, Song song) {
		this.favoriteSongId = favoriteSongDto.getFavoriteSongId();
		this.userId = favoriteSongDto.getUserId();
		this.song = song;
		this.isLike = favoriteSongDto.getIsLike();
	}

	// Entity to Dto
	public SongDto toDto() {
		return new SongDto(song.getSongId(), song.getTitle(), song.getSinger(), song.getAlbum(), song.getImage(), song.getReleaseDate());
	}
}
