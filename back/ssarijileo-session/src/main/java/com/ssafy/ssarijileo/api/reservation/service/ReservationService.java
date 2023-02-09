package com.ssafy.ssarijileo.api.reservation.service;

import java.util.List;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;

public interface ReservationService {

	List<ReservationDto> findReservationBySessionId(String sessionId);

	void createReservaion(String sessionId);

	void insertReservation(ReservationDto reservationDto);

	void deleteReservation(ReservationDto reservationDto);
}
