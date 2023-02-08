package com.ssafy.ssarijileo.api.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.api.song.entity.Pitch;

@Repository
public interface PitchJpaRepository extends JpaRepository<Pitch, Long> {
}
