package com.ssafy.ssarijileo.api.reservation.service;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.reservation.client.ReservationClient;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private final ReservationClient reservationClient;

	@Override
	public void insertReservation(ReservationDto reservationDto) {
		reservationClient.insertReservation(reservationDto);
	}

	@Override
	public void deleteReservation(ReservationDeleteDto reservationDeleteDto) {
		reservationClient.deleteReservation(reservationDeleteDto);
	}
}
