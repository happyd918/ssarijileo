package com.ssafy.ssarijileo.api.songsetting.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SongSettingDto {

	// PK
	private String songSettingId;

	// 에코
	private int eco;

	// 음량
	private int volume;
}
