package com.ssafy.ssarijileo.api.reservation.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;

@FeignClient(name = "reservation-client", url = "localhost:8070/api/v1/reservation")
public interface ReservationClient {

	@PostMapping
	void insertReservation(@RequestBody ReservationDto reservationDto);

	@DeleteMapping
	void deleteReservation(@RequestBody ReservationDeleteDto reservationDeleteDto);
}
