package com.ssafy.ssarijileo.sse.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

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
	public SseEmitter connection(String userId) {
		SseEmitter emitter = sseRepository.save(userId, new SseEmitter(DEFAULT_TIMEOUT));

		emitter.onCompletion(() -> sseRepository.remove(userId));
		emitter.onTimeout(() -> sseRepository.remove(userId));

		try {
			log.info(System.currentTimeMillis() + " send to " + userId);
			emitter.send(SseEmitter.event()
				.id(userId)
				.name("sse connection")
				.data("connect completed"));
		} catch (IOException exception) {
			throw new AlarmException();
		}

		System.out.println(userId + "님의 emitter" + emitter);
		return emitter;
	}

	@Override
	public void sendFriendRequest(FriendRequestEvent event) {
		System.out.println(event.getUser().getFromUserId()+"님이 "+event.getUser().getToUserId()+"님에게 친구 요청");
		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(it -> {
				try {
					System.out.println("친구 요청 in try");
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
		System.out.println(event.getUser().getFromUserId()+"님이 "+event.getUser().getToUserId()+"님에게 친구 초대");
		sseRepository.get(event.getUser().getToUserId()).ifPresentOrElse(it -> {
				try {
					System.out.println("친구 초대 in try");
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
