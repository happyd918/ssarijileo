package com.ssafy.ssarijileo.api.user.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import com.ssafy.ssarijileo.common.exception.NotFoundException;
import com.ssafy.ssarijileo.api.user.entity.User;
import com.ssafy.ssarijileo.api.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Override
	public void withdrawlUser(String userId) {
		User user = userRepository.findByUserId(UUID.fromString(userId)).orElseThrow(NotFoundException::new);
		user.updateStatus("X");
	}
}
