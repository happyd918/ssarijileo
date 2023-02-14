package com.ssafy.ssarijileo.api.ranking.client;

import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.dto.RankingType;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(name = "ranking-client", url = "192.168.49.2:31000/api/v1/ranking")
public interface RankingClient {

    @GetMapping
    List<RankingDto> getRanking(RankingType rankingType);

    @PostMapping
    void setRanking(RankingType rankingType, List<RankingDto> list);

}
