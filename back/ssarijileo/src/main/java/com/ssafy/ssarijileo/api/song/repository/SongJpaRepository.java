package com.ssafy.ssarijileo.api.song.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.api.song.entity.Song;

@Repository
public interface SongJpaRepository extends JpaRepository<Song, Long> {
}
