package com.tyss.bookmydoctor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.dto.DoctorDto;
import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.dto.SpecialistDto;
import com.tyss.bookmydoctor.api.service.DoctorService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class DoctorController {

	@Autowired
	private DoctorService service;

	@PostMapping("doctor/register")
	@Operation(summary = "Register Doctor API")
	public ResponseEntity<ResponseDto> registerDoctor(@RequestBody DoctorDto doctorDto) {
		log.info("Entered into Doctor register controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.DOCTOR_REGISTER_SUCCESS, service.register(doctorDto)));
  
	}
 
	@GetMapping("/doctor/login")
	@Operation(summary = "Login Doctor API")
	public ResponseEntity<ResponseDto> doctorLogin(LoginDto loginDto) {
		log.info("Entered into Doctor login controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.DOCTOR_LOGIN_SUCCESS, service.login(loginDto)));

	}
 
	@GetMapping("/doctors")
	@Operation(summary = "Get All Doctors API")
	public ResponseEntity<ResponseDto> getAll() {
		log.info("Entered into Doctor getAll controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.DOCTOR_GETALL_SUCCESS, service.getAll()));

	}

	@GetMapping("/doctor")
	@Operation(summary = "Search Doctor By Location or Speciality")
	public ResponseEntity<ResponseDto> searchDoctor(@RequestParam String location, @RequestParam String speciality) {
		log.info("Entered into Doctor searchDoctor controller");
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.DOCTOR_SEARCH_SUCCESSS, service.search(location, speciality)));
 
	}

	@PostMapping("/add/specialist")
	@Operation(summary = "Save Specialist For Doctor API")
	public ResponseEntity<ResponseDto> saveSpecialist(@RequestBody SpecialistDto specialistDto) {
		log.info("Entered into Doctor saveSpecialist controller");
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.SPECIALIST_SAVE_SUCCESS, service.saveSpecialist(specialistDto)));

	}

	@PostMapping("/leaveRequest")
	@Operation(summary = "Apply Leave Request API")
	public ResponseEntity<ResponseDto> leaveRequest(@RequestBody LeaveRequestDto requestDto) {
		log.info("Entered into Doctor leaveRequest controller");
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.DOCTOR_LEAVE_REQUEST_SUCCESS, service.leaveRequest(requestDto)));

	}
}
