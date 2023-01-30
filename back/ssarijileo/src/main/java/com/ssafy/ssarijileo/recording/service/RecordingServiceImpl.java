package com.ssafy.ssarijileo.recording.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.exception.NotFoundException;
import com.ssafy.ssarijileo.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.recording.entity.Recording;
import com.ssafy.ssarijileo.recording.repository.RecordingJpaRepository;
import com.ssafy.ssarijileo.song.entity.Song;
import com.ssafy.ssarijileo.song.repository.SongJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecordingServiceImpl implements RecordingService{

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
	public void insertRecording(RecordingDto recordingDto) {
		Song song = songJpaRepository.findById(recordingDto.getSongId()).orElseThrow(NotFoundException::new);
		Recording recording = Recording.builder().recordingDto(recordingDto).song(song).build();
		recordingJpaRepository.save(recording);
	}
}
