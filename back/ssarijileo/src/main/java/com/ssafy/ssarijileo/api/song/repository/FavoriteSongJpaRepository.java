package com.ssafy.ssarijileo.api.song.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.ssarijileo.api.song.entity.FavoriteSong;

public interface FavoriteSongJpaRepository extends JpaRepository<FavoriteSong, Long> {

	Optional<List<FavoriteSong>> findByUserId(String userId);
}
