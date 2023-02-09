package com.ssafy.ssarijileo.api.reservation.service;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;

public interface ReservationService {

	void insertReservation(ReservationDto reservationDto);

	void deleteReservation(ReservationDeleteDto reservationDeleteDto);
}
