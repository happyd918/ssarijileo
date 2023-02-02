package com.ssafy.ssarijileo.api.recording.service;

import java.util.List;

import com.ssafy.ssarijileo.api.recording.dto.RecordingDto;

public interface RecordingService {

	List<RecordingDto> findAllRecording();

	RecordingDto findRecordingById(Long recordingId);

	List<RecordingDto> findRecordingByUserId(String userId);

	void insertRecording(RecordingDto recordingDto);
}
