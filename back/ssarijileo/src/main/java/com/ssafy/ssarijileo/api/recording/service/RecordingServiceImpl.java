package com.ssafy.ssarijileo.api.recording.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.api.recording.entity.Recording;
import com.ssafy.ssarijileo.api.recording.repository.RecordingJpaRepository;
import com.ssafy.ssarijileo.api.song.entity.Song;
import com.ssafy.ssarijileo.api.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordingServiceImpl implements RecordingService {

	private final RecordingJpaRepository recordingJpaRepository;
	private final SongJpaRepository songJpaRepository;

	@Override
	public List<RecordingDto> findAllRecording() {
		return recordingJpaRepository.findAll().stream().map(Recording::toDto).collect(Collectors.toList());
	}

	@Override
	public RecordingDto findRecordingById(Long id) {
		return recordingJpaRepository.findById(id).orElseThrow(NotFoundException::new).toDto();
	}

	@Override
	public List<RecordingDto> findRecordingByUserId(String userId) {
		return recordingJpaRepository.findRecordingByUserId(userId)
			.orElseThrow(NotFoundException::new)
			.stream()
			.map(Recording::toDto)
			.collect(Collectors.toList());
	}

	@Override
	public void insertRecording(RecordingDto recordingDto) {
		Song song = songJpaRepository.findById(recordingDto.getSongId()).orElseThrow(NotFoundException::new);
		Recording recording = Recording.builder().recordingDto(recordingDto).song(song).build();
		recordingJpaRepository.save(recording);
	}
}
