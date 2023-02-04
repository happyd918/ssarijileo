package com.ssafy.ssarijileo.api.songsetting.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.ssafy.ssarijileo.api.profile.entitiy.Profile;
import com.ssafy.ssarijileo.api.songsetting.dto.SongSettingDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SongSetting {

	// PK
	@Id
	private String songSettingId;

	// 에코
	private int eco;

	// 음량
	private int volume;

	@Builder
	public SongSetting(SongSettingDto songSettingDto) {
		this.songSettingId = songSettingDto.getSongSettingId();
		this.eco = songSettingDto.getEco();
		this.volume = songSettingDto.getVolume();
	}

	public SongSettingDto toDto() {
		return new SongSettingDto(songSettingId, eco, volume);
	}

	public void updateSetting(int eco, int volume) {
		this.eco = eco;
		this.volume = volume;
	}
}
