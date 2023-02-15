// package com.ssafy.ssarijileo.api.ranking.controller;
//
// import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
// import com.ssafy.ssarijileo.api.ranking.dto.RankingType;
// import com.ssafy.ssarijileo.api.ranking.service.RankingService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import java.util.List;
//
// @RequiredArgsConstructor
// @RestController
// @RequestMapping("/api/v1/ranking")
// public class RankingController {
//
//     private final RankingService rankingService;
//
//     @GetMapping
//     public List<RankingDto> getRanking(RankingType rankingType) {
//         return rankingService.getRanking(rankingType);
//     }
//
//     @PostMapping
//     public void setRanking(RankingType rankingType, List<RankingDto> list) {
//         rankingService.setRanking(rankingType, list);
//     }
//
// }
