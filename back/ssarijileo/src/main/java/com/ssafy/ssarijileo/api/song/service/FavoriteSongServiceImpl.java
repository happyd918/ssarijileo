package com.ssafy.ssarijileo.api.song.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteSongServiceImpl implements FavoriteSongService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void subscribe(String userId, Long songId) {
        redisTemplate.opsForSet().add("subscribe:" + songId, userId);
    }

    @Override
    public void unsubscribe(String userId, Long songId) {
        redisTemplate.opsForSet().remove("subscribe:" + songId, userId);
    }

    @Override
    public Boolean hasSubscribed(String userId, Long songId) {
        return redisTemplate.opsForSet().isMember("subscribe:" + songId, userId);
    }
}
