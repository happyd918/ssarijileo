package com.ssafy.ssarijileo.api.songsetting.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
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
}
