package com.ssafy.ssarijileo.recording.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.recording.entity.Recording;

@Repository
public interface RecordingJpaRepository extends JpaRepository<Recording, Long> {
}
