package com.tyss.bookmydoctor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.dto.AppointmentDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.service.AppointmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AppointmentController {

	@Autowired
	private AppointmentService service;

	@PostMapping("/book")
	@Operation(summary = "Book Appointment API")
	public ResponseEntity<ResponseDto> bookAppointment(@RequestBody AppointmentDto appointmentDto) {
		log.info("Entered into Appointment bookAppointment controller");
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.APPOINTMENT_BOOK_SUCCESS, service.book(appointmentDto)));

	} 
 
	@GetMapping("/appointments")
	@Operation(summary = "Get All Appointments API")
	public ResponseEntity<ResponseDto> getAllAppointments() {
		log.info("Entered into Appointment getAll controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.APPOINTMENT_GETALL_SUCCESS, service.getAll()));

	}

	@GetMapping("/appointment/{id}")
	@Operation(summary = "Search Appointments by Id API")
	public ResponseEntity<ResponseDto> get(@Parameter(description = "Enter Doctor ID") Integer id) {
		log.info("Entered into Appointment get controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.APPOINTMENT_GET_SUCCESS, service.get(id)));

	}
}
