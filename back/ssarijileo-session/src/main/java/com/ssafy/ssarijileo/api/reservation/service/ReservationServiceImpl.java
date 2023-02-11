package com.ssafy.ssarijileo.api.reservation.service;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssarijileo.api.singing.client.SingingClient;
import com.ssafy.ssarijileo.api.singing.dto.SingingDto;
import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;
import com.ssafy.ssarijileo.api.reservation.repository.ReservationRepository;
import com.ssafy.ssarijileo.common.exception.NotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final ReservationRepository reservationRepository;
	private final SingingClient singingClient;

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

		SingingDto singingDto = SingingDto.builder().userId(reservationDto.getUserId()).songId(reservationDto.getSongId()).state("I").build();
		singingClient.insertSinging(singingDto);
	}

	@Override
	public void deleteReservation(ReservationDto reservationDto) {
		List<ReservationDto> list = reservationRepository.get(reservationDto.getSessionId()).orElseThrow(
			NotFoundException::new);
		list.add(reservationDto);
		reservationRepository.set(reservationDto.getSessionId(), list);

		SingingDto singingDto = SingingDto.builder().userId(reservationDto.getUserId()).songId(reservationDto.getSongId()).state("C").build();
		singingClient.insertSinging(singingDto);
	}
}
