package com.ssafy.ssarijileo.api.ranking.service;

import com.ssafy.ssarijileo.api.ranking.dto.RankingDto;
import com.ssafy.ssarijileo.api.ranking.dto.RankingType;
import com.ssafy.ssarijileo.api.singing.entity.Singing;
import com.ssafy.ssarijileo.api.singing.repsoitory.SingingJpaRepository;
import com.ssafy.ssarijileo.api.song.entity.FavoriteSong;
import com.ssafy.ssarijileo.api.song.entity.Song;
import com.ssafy.ssarijileo.api.song.repository.FavoriteSongJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class RankingServiceImpl implements RankingService {

    private final SingingJpaRepository singingJpaRepository;
    private final FavoriteSongJpaRepository favoriteSongJpaRepository;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<RankingDto> findDailyRanking(String userId) {
        List<RankingDto> list = findRanking(RankingType.DAY);

        if (userId.isEmpty())
            return list;

        for (RankingDto dto : list) {
            dto.updateLike(redisTemplate.opsForSet().isMember("subscribe:" + userId, dto.getSongId()));
        }

        return list;
    }

    @Override
    public List<RankingDto> findWeeklyRanking(String userId) {
        List<RankingDto> list = findRanking(RankingType.WEEK);

        if (userId.isEmpty())
            return list;

        for (RankingDto dto : list) {
            dto.updateLike(redisTemplate.opsForSet().isMember("subscribe:" + userId, dto.getSongId()));
        }

        return list;
    }

    @Override
    public List<RankingDto> findMonthlyRanking(String userId) {
        List<RankingDto> list = findRanking(RankingType.MONTH);

        if (userId.isEmpty())
            return list;

        for (RankingDto dto : list) {
            dto.updateLike(redisTemplate.opsForSet().isMember("subscribe:" + userId, dto.getSongId()));
        }

        return list;
    }

    @Override
    public List<RankingDto> findRanking(RankingType rankingType) {

        /////// 날짜 구분
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String startDate = "";
        String endDate = "";
        String startTime = " 00:00:00";
        String endTime = " 23:59:59";

        switch(rankingType) {
            case DAY:
                startDate = LocalDate.now().minusDays(1).format(dateFormat) + startTime;
                endDate = LocalDate.now().minusDays(1).format(dateFormat) + endTime;
                break;
            case WEEK:
                startDate = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).minusDays(6).format(dateFormat) + startTime;
                endDate = LocalDateTime.now().with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).format(dateFormat) + endTime;
                break;
            case MONTH:
                startDate = LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.firstDayOfMonth()).format(dateFormat) + startTime;
                endDate = LocalDateTime.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth()).format(dateFormat) + endTime;
                break;
            default: break;
        }
        log.info("start date = {}, end date = {}", startDate, endDate);

        ///////

        List<Singing> singingList = singingJpaRepository.findBySingingDateBetween(startDate, endDate);
        List<FavoriteSong> favoriteSongList = favoriteSongJpaRepository.findByRegisterDateBetween(startDate, endDate);

        // 랭킹 계산
        Map<Long, Double> map = new HashMap<>();
        Map<Long, Song> songMap = new HashMap<>();

        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("H:mm:ss");

        // 날짜간 부른 횟수 계산
        for (Singing singing : singingList) {
            // 현재 노래 정보
            Song song = singing.getSong();
            // songId
            Long key = song.getSongId();
            // 부른 횟수
            Double val = 1.0;

            // 노래 부른 기록일 경우
            if (singing.getState().equals("I")) {
                if (map.containsKey(key)) {
                    val += map.get(key);
                }
            } else {    // 노래 취소한 기록일 경우

                // 원곡 시간
                double originSongTime = LocalTime.parse(singing.getSong().getTime(), timeFormat).toSecondOfDay();
                // 부른 시간
                double singTime = LocalTime.parse(singing.getSingingTime(), timeFormat).toSecondOfDay();
                
                // 1 - (부른 시간 / 원곡 시간)
                val = 1 - (singTime / originSongTime);
                if (map.containsKey(key)) {
                    val = map.get(key) - val;
                }
            }
            // 노래 랭킹점수 저장
            map.put(key, val);

            // 노래 정보 저장
            songMap.put(key, song);
        }

//        // 날짜간 애창곡 지정 횟수 계산
//        for (FavoriteSong favoriteSong : favoriteSongList) {
//            // 현재 노래 정보
//            Song song = favoriteSong.getSong();
//            // songId
//            Long key = song.getSongId();
//            // 애창곡으로 지정된 횟수 * 가중치
//            Double val = 1 * 10.0;
//
//            if (favoriteSong.getIsLike().equals("Y")) {
//                if (map.containsKey(key)) {
//                    val += map.get(key);
//                }
//            } else {
//                if (map.containsKey(key)) {
//                    val = map.get(key) - val;
//                }
//            }
//            // 노래 랭킹점수 저장
//            map.put(key, val);
//
//            // 노래 정보 저장
//            songMap.put(key, song);
//        }

        // 점수 순으로 정렬
        List<Long> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1, o2) -> map.get(o2).compareTo(map.get(o1)));

        List<RankingDto> rankingList = new ArrayList<>();

        // 순위 지정
        int ranking = 1;
        for (Long key : keySet) {
            Song song = songMap.get(key);
            rankingList.add(
                RankingDto.builder()
                        .ranking(ranking++)
                        .songId(song.getSongId())
                        .title(song.getTitle())
                        .singer(song.getSinger())
                        .album(song.getAlbum())
                        .image(song.getImage()).build()
            );
            
            // Top 100 까지만
            if (ranking > 100) break;
        }
        return rankingList;
    }
}
