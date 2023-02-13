package com.ssafy.ssarijileo.sse.service;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.util.ContentCachingResponseWrapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.ssarijileo.common.exception.AlarmException;
import com.ssafy.ssarijileo.api.friend.dto.FriendInviteEvent;
import com.ssafy.ssarijileo.api.friend.dto.FriendRequestEvent;
import com.ssafy.ssarijileo.sse.repository.SseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

	private final SseRepository sseRepository;

	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	@Override
	public void connection(HttpServletResponse response, String userId) throws IOException {
		System.out.println("connect HttpServletResponse : " + response);

		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		sseRepository.save(userId, response);

		Writer writer = response.getWriter();
		writer.write("data: sse connection\n\n");
		writer.flush();
		writer.close();
	}

	@Override
	public void sendFriendRequest(FriendRequestEvent event) throws IOException {
		System.out.println(event.getUser().getFromUserId() + "님이 " + event.getUser().getToUserId() + "님에게 친구 요청");

		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(response -> {
				response.setContentType("text/event-stream");
				response.setCharacterEncoding("UTF-8");
				Writer writer = null;
				try {
					writer = response.getWriter();
					ObjectMapper mapper = new ObjectMapper();
					writer.write(mapper.writeValueAsString(event));
					writer.flush();
					writer.close();
				} catch (IOException e) {
					System.out.println("request exception : " + e.getMessage());
					sseRepository.remove(event.getUser().getToUserId());
					throw new AlarmException("친구요청 알림 전송 중 오류가 발생했습니다.");
				}
			},
			() -> log.info("No friend request emitter founded")
		);
	}

	@Override
	public void sendFriendInvite(FriendInviteEvent event) throws IOException {
		System.out.println(event.getUser().getFromUserId() + "님이 " + event.getUser().getToUserId() + "님에게 친구 초대");

		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(response -> {
				response.setContentType("text/event-stream");
				response.setCharacterEncoding("UTF-8");
				Writer writer = null;
				try {
					writer = response.getWriter();
					ObjectMapper mapper = new ObjectMapper();
					writer.write(mapper.writeValueAsString(event));
					writer.flush();
					writer.close();
				} catch (IOException e) {
					System.out.println("invite exception : " + e.getMessage());
					sseRepository.remove(event.getUser().getToUserId());
					throw new AlarmException("친구 초대 알림 전송 중 오류가 발생했습니다.");
				}
			},
			() -> log.info("No invite request emitter founded")
		);
	}
}
