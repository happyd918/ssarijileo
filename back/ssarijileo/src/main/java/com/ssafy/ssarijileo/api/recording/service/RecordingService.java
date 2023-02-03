package com.ssafy.ssarijileo.api.recording.service;

import java.util.List;

import com.ssafy.ssarijileo.api.recording.dto.RecordingDto;
import com.ssafy.ssarijileo.api.recording.dto.RecordingResponseDto;

public interface RecordingService {

	List<RecordingResponseDto> findRecordingByUserId(String userId);

	void insertRecording(RecordingDto recordingDto);
}
