package com.ssafy.ssarijileo.api.reservation.service;

import org.springframework.stereotype.Service;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;
import com.ssafy.ssarijileo.api.reservation.repository.ReservationRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService{

	private final ReservationRepository reservationRepository;

	@Override
	public void insertReservation(ReservationDto reservationDto) {

	}

	@Override
	public void deleteReservation(ReservationDeleteDto reservationDeleteDto) {

	}
}
