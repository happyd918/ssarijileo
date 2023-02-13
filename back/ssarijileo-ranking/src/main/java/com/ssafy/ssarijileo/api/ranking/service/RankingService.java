package com.ssafy.ssarijileo.api.ranking.service;


import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.dto.RankingType;

import java.text.ParseException;
import java.util.List;

public interface RankingService {

    List<RankingDto> findDailyRanking(String userId);

    List<RankingDto> findWeeklyRanking(String userId);

    List<RankingDto> findMonthlyRanking(String userId);

    List<RankingDto> findRanking(RankingType rankingType);
}
