package com.ssafy.ssarijileo.api.ranking.controller;

import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.service.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping("/daily")
    public ResponseEntity<List<RankingDto>> findDailyRanking(@RequestHeader Optional<String> userId) {
        return ResponseEntity.status(200).body(rankingService.findDailyRanking(userId.orElse(new String())));
    }
    @GetMapping("/weekly")
    public ResponseEntity<List<RankingDto>> findWeeklyRanking(@RequestHeader Optional<String> userId) {
        return ResponseEntity.status(200).body(rankingService.findWeeklyRanking(userId.orElse(new String())));
    }
    @GetMapping("/monthly")
    public ResponseEntity<List<RankingDto>> findMonthlyRanking(@RequestHeader Optional<String> userId) {
        return ResponseEntity.status(200).body(rankingService.findMonthlyRanking(userId.orElse(new String())));
    }
}
