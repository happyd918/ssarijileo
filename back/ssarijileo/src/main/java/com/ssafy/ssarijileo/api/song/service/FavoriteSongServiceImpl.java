package com.ssafy.ssarijileo.api.song.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Transactional
public class FavoriteSongServiceImpl implements FavoriteSongService {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void subscribe(String userId, Long songId) {
        redisTemplate.opsForSet().add("subscribe:" + userId, String.valueOf(songId));
        redisTemplate.expire("subscribe:" + userId, 1, TimeUnit.DAYS);
    }

    @Override
    public void unsubscribe(String userId, Long songId) {
        redisTemplate.opsForSet().remove("subscribe:" + userId, String.valueOf(songId));
    }

    @Override
    public Boolean hasSubscribed(String userId, Long songId) {
        return redisTemplate.opsForSet().isMember("subscribe:" + userId, String.valueOf(songId));
    }

    @Override
    public Set<String> getUsersFavoriteSong(String userId) {
        return redisTemplate.opsForSet().members("subscribe:" + userId);
    }

    @Override
    public Set<String> getKeys() {
        return redisTemplate.keys("subscribe:*");
    }

    @Override
    public Boolean hasKey(String userId) {
        return redisTemplate.hasKey("subscribe:" + userId);
    }

    public void removeAll(String userId) {
        for (int i = 1; i <= 31; i++)
            redisTemplate.opsForSet().remove("subscribe:" + userId, String.valueOf(i));
    }

}
