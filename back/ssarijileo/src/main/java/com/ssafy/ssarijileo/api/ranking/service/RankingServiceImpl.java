package com.ssafy.ssarijileo.api.ranking.service;

import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.dto.RankingType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingServiceImpl {

    private final RedisTemplate<String, Object> redisTemplate;

    public void setRanking(RankingType rankingType, List<RankingDto> list) {
        // 기존 랭킹 삭제
        redisTemplate.opsForList().leftPop("ranking:" + rankingType.toString());

        // 랭킹 대체
        redisTemplate.opsForList().leftPush("ranking:" + rankingType.toString(), list);

        // 만료 기간
        int timeout;
        switch (rankingType) {
            case MONTH: timeout = 30; break;
            case WEEK: timeout = 7; break;
            default: timeout = 1; break;
        }
        redisTemplate.expire("ranking:" + rankingType.toString(), 1, TimeUnit.DAYS);
    }

    public List<RankingDto> getRanking(RankingType rankingType) {
        return redisTemplate
                .opsForList()
                .range("ranking:" + rankingType.toString(), 0, -1)
                .stream()
                .map(obj -> (RankingDto) obj)
                .collect(Collectors.toList());
    }

}
