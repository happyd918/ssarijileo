package com.ssafy.ssarijileo.api.ranking.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class RankingDto {

    // 순위
    private final int ranking;

    // 노래 ID
    private final Long songId;

    // 노래 제목
    private final String title;

    // 가수
    private final String singer;
    
    // 앨범 제목
    private final String album;
    
    // 앨범 이미지
    private final String image;

}
