// package com.ssafy.ssarijileo.api.reservation.controller;
//
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
//
// import com.ssafy.ssarijileo.api.reservation.dto.ReservationDeleteDto;
// import com.ssafy.ssarijileo.api.reservation.dto.ReservationDto;
// import com.ssafy.ssarijileo.api.reservation.service.ReservationService;
// import com.ssafy.ssarijileo.common.model.BaseResponseBody;
//
// import io.swagger.annotations.Api;
// import io.swagger.annotations.ApiOperation;
// import io.swagger.annotations.ApiResponse;
// import io.swagger.annotations.ApiResponses;
// import lombok.RequiredArgsConstructor;
//
// @Api(tags = "노래예약 API")
// @RestController
// @RequestMapping("/api/v1/reservation")
// @RequiredArgsConstructor
// public class ReservationController {
//
// 	private final ReservationService reservationService;
//
// 	/**
// 	 * @title 노래 예약
// 	 * @param reservationDto
// 	 * @return
// 	 */
// 	@ApiOperation(
// 		value = "노래 예약",
// 		notes = "어떤 노래방에서 어떤 사용자가 어떤 노래를 예약했는지 저장한다."
// 	)
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "성공"),
// 		@ApiResponse(code = 401, message = "인증 실패"),
// 		@ApiResponse(code = 404, message = "정보 없음"),
// 		@ApiResponse(code = 500, message = "서버 오류")
// 	})
// 	@PostMapping
// 	ResponseEntity<? extends BaseResponseBody> insertReservation(@RequestBody ReservationDto reservationDto) {
// 		reservationService.insertReservation(reservationDto);
// 		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
// 	}
//
// 	/**
// 	 * @title 예약 취소
// 	 * @param reservationDeleteDto
// 	 * @return
// 	 */
// 	@ApiOperation(
// 		value = "예약 취소",
// 		notes = "예약된 노래를 취소한다."
// 	)
// 	@ApiResponses({
// 		@ApiResponse(code = 200, message = "성공"),
// 		@ApiResponse(code = 401, message = "인증 실패"),
// 		@ApiResponse(code = 404, message = "정보 없음"),
// 		@ApiResponse(code = 500, message = "서버 오류")
// 	})
// 	@DeleteMapping
// 	ResponseEntity<? extends BaseResponseBody> deleteReservation(@RequestBody ReservationDeleteDto reservationDeleteDto) {
// 		reservationService.deleteReservation(reservationDeleteDto);
// 		return ResponseEntity.status(200).body(BaseResponseBody.of(200, "Success"));
// 	}
// }
