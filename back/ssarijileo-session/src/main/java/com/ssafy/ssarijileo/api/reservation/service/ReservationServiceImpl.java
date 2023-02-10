package com.ssafy.ssarijileo.api.reservation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;
import com.ssafy.ssarijileo.api.reservation.repository.ReservationRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;

	@Override
	public List<ReservationDto> findReservationBySessionId(String sessionId) {
		return reservationRepository.get(sessionId).orElseThrow(NotFoundException::new);
	}

	@Override
	public void createReservation(String sessionId) {
		reservationRepository.set(sessionId, new ArrayList<ReservationDto>());
	}

	@Override
	public void insertReservation(ReservationDto reservationDto) {
		List<ReservationDto> list = reservationRepository.get(reservationDto.getSessionId()).orElseThrow(
			NotFoundException::new);
		list.add(reservationDto);
		reservationRepository.set(reservationDto.getSessionId(), list);
	}

	@Override
	public void deleteReservation(ReservationDto reservationDto) {
		List<ReservationDto> list = reservationRepository.get(reservationDto.getSessionId()).orElseThrow(
			NotFoundException::new);
		list.add(reservationDto);
		reservationRepository.set(reservationDto.getSessionId(), list);
	}
}
