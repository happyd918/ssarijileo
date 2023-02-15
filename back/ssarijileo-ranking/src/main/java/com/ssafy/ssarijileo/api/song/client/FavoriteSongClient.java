package com.ssafy.ssarijileo.api.song.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

// @FeignClient(name = "favorite-song-client", url = "192.168.49.2:31000/api/v1/favorite-song")
@FeignClient(name = "favorite-song-client", url = "localhost:8080/api/v1/favorite-song")
public interface FavoriteSongClient {

    @GetMapping
    Boolean isFavoriteSong(String userId, Long songId);

}
