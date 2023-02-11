package com.ssafy.ssarijileo.api.reservation.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;

@FeignClient(name = "reservation-client", url = "session:8070/api/v1/reservation")
public interface ReservationClient {

	@GetMapping("/{sessionId}")
	List<ReservationDto> findReservationBySessionId(String sessionId);

	@PostMapping
	void insertReservation(@RequestBody ReservationDto reservationDto);

	@DeleteMapping
	void deleteReservation(@RequestBody ReservationDto reservationDto);
}
