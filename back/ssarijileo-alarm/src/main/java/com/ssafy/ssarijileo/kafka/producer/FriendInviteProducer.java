package com.ssafy.ssarijileo.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ssafy.ssarijileo.kafka.event.FriendInviteEvent;
import com.ssafy.ssarijileo.common.model.AlarmType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendInviteProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	private String topic = "friend invite";

	public void send(FriendInviteEvent event) {
		// (Topic, key, data)
		kafkaTemplate.send(topic, event.getUser().getToUserId(), event);
		log.info("AlarmEvent sent to kafka topic: {}", event);
	}
}
