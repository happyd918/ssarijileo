package com.ssafy.ssarijileo.api.ranking.service;


import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.dto.RankingType;

import java.text.ParseException;
import java.util.List;

public interface RankingService {

    List<RankingDto> findDailyRanking();

    List<RankingDto> findWeeklyRanking();

    List<RankingDto> findMonthlyRanking();

    List<RankingDto> findRanking(RankingType rankingType);
}
