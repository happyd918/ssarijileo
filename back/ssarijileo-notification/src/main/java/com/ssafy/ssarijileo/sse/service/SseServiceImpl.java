package com.ssafy.ssarijileo.sse.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.ssafy.ssarijileo.common.exception.AlarmException;
import com.ssafy.ssarijileo.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.kafka.event.FriendRequestEvent;
import com.ssafy.ssarijileo.sse.repository.SseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class SseServiceImpl implements SseService {

	private final static String ALARM_NAME = "alarm";

	private final SseRepository sseRepository;

	private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60;

	@Override
	public SseEmitter connection(String userId) {
		SseEmitter emitter = sseRepository.save(userId, new SseEmitter(DEFAULT_TIMEOUT));

		emitter.onCompletion(() -> sseRepository.remove(userId));
		emitter.onTimeout(() -> sseRepository.remove(userId));

		try {
			log.info(System.currentTimeMillis() + " send to " + userId);
			emitter.send(SseEmitter.event()
				.name(ALARM_NAME)
				.data("connect completed"));
		} catch (IOException exception) {
			throw new AlarmException();
		}

		return emitter;
	}

	@Override
	public void sendFriendRequest(FriendRequestEvent event) {
		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(it -> {
				try {
					it.send(SseEmitter.event()
						.id(event.getUser().getToUserId())
						.name("friend request")
						.data(event));
				} catch (IOException exception) {
					sseRepository.remove(event.getUser().getToUserId());
					throw new AlarmException("친구요청 알림 전송 중 오류가 발생했습니다.");
				}
			},
			() -> log.info("No friend invite emitter founded")
		);
	}

	@Override
	public void sendFriendInvite(FriendInviteEvent event) {
		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(it -> {
				try {
					it.send(SseEmitter.event()
						.id(event.getUser().getToUserId())
						.name("friend invite")
						.data(event));
				} catch (IOException exception) {
					sseRepository.remove(event.getUser().getToUserId());
					throw new AlarmException("친구초대 알림 전송 중 오류가 발생했습니다.");
				}
			},
			() -> log.info("No friend request emitter founded")
		);
	}
}
