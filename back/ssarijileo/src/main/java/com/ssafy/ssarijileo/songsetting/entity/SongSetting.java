package com.ssafy.ssarijileo.songsetting.entity;

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
	String songSettingId;

	// 에코
	int eco;

	// 음량
	int volume;
}
