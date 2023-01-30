package com.ssafy.ssarijileo.singing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssarijileo.singing.entity.Singing;

@Repository
public interface SingingJpaRepository extends JpaRepository<Singing, Long> {
}
