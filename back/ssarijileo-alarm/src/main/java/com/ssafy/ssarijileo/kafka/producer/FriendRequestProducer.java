package com.ssafy.ssarijileo.kafka.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.ssafy.ssarijileo.kafka.event.FriendRequestEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class FriendRequestProducer {

	private final KafkaTemplate<String, Object> kafkaTemplate;

	private String topic = "friend request";

	public void send(FriendRequestEvent event) {
		// (Topic, key, data)
		kafkaTemplate.send(topic, event.getUser().getToUserId(), event);
		log.info("Friend Request Alarm sent to kafka topic: {}", event);
	}
}
