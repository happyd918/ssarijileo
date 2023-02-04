package com.ssafy.ssarijileo.api.singingcontest.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.recording.entity.Recording;
import com.ssafy.ssarijileo.api.recording.repository.RecordingJpaRepository;
import com.ssafy.ssarijileo.api.singingcontest.dto.SingingContestResponseDto;
import com.ssafy.ssarijileo.api.singingcontest.dto.SingingContestUpdateDto;
import com.ssafy.ssarijileo.api.singingcontest.entity.SingingContest;
import com.ssafy.ssarijileo.api.singingcontest.repository.SingingContestJpaRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class SingingContestServiceImpl implements SingingContestService{

	private final SingingContestJpaRepository singingContestJpaRepository;
	private final RecordingJpaRepository recordingJpaRepository;

	@Override
	public List<SingingContestResponseDto> findAllSingingContest() {
		return singingContestJpaRepository.findAll().stream().map(SingingContest::toDto).collect(Collectors.toList());
	}

	@Override
	public List<SingingContestResponseDto> findSingingContestByUserId(String userId) {
		return singingContestJpaRepository.findByRecording_Profile_ProfileId(userId).orElseThrow(NumberFormatException::new)
			.stream().map(SingingContest::toDto).collect(Collectors.toList());
	}

	@Override
	public void insertSingingContest(Long recordingId) {
		Recording recording = recordingJpaRepository.findById(recordingId).orElseThrow(NotFoundException::new);
		SingingContest singingContest = SingingContest.builder().recording(recording).build();
		singingContestJpaRepository.save(singingContest);
	}

	@Override
	public void updateSingingContest(SingingContestUpdateDto singingContestUpdateDto) {
		SingingContest singingContest = singingContestJpaRepository.findById(
			singingContestUpdateDto.getSingingContestId()).orElseThrow(NotFoundException::new);
		singingContest.updateStatus(singingContest.getStatus());
	}
}
