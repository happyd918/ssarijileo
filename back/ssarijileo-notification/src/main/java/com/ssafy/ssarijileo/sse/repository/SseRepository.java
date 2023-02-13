package com.ssafy.ssarijileo.sse.repository;

import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.util.ContentCachingResponseWrapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j // Simple Logging Facade for Java
@Repository
public class SseRepository {
	private Map<String, HttpServletResponse> sseMap = new HashMap<>();

	public void save(String id, HttpServletResponse sse) {
		sseMap.put(id, sse);
		System.out.println("repository save : "+sseMap.size());
		log.info("EmitterRepository save : {}", id);
	}

	public Optional<HttpServletResponse> get(String id) {
		log.info("EmitterRepository get : {}", id);
		System.out.println("repository get : "+sseMap.size());
		return Optional.ofNullable(sseMap.get(id));
	}

	public void remove(String id) {
		log.info("EmitterRepository remove : {}", id);
		System.out.println("repository remove : "+sseMap.size());
		sseMap.remove(id);
	}
}
