package com.ssafy.ssarijileo.api.song.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ssafy.ssarijileo.api.song.entity.FavoriteSong;

public interface FavoriteSongJpaRepository extends JpaRepository<FavoriteSong, Long> {

	// Optional<List<FavoriteSong>> findByUserId(String userId);

	@Query("SELECT favoriteSong.songId FROM FavoriteSong favoriteSong WHERE favoriteSong.userId = :userId ORDER BY favoriteSong.registerDate DESC")
	String[] findLatestSongIdByUserId(String userId);

}
