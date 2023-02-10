package com.ssafy.ssarijileo.api.singingcontest.service;

import java.util.List;

import com.ssafy.ssarijileo.api.singingcontest.dto.SingingContestResponseDto;
import com.ssafy.ssarijileo.api.singingcontest.dto.SingingContestUpdateDto;

public interface SingingContestService {

	List<SingingContestResponseDto> findAllSingingContest();

	List<SingingContestResponseDto> findSingingContestByUserId(String userId);

	void insertSingingContest(Long recordingId);

	void updateSingingContest(SingingContestUpdateDto singingContestUpdateDto);
}