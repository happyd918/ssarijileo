package com.ssafy.ssarijileo.api.reservation.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;
import com.ssafy.ssarijileo.api.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/v1/reservation", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationService reservationService;

	@PostMapping
	void insertReservation(@RequestBody ReservationDto reservationDto) {
		reservationService.insertReservation(reservationDto);
	}

	@DeleteMapping
	void deleteReservation(@RequestBody ReservationDeleteDto reservationDeleteDto) {
		reservationService.deleteReservation(reservationDeleteDto);
	}
}
