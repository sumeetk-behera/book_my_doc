package com.tyss.bookmydoctor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.service.AdminService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("admin/login")
	@Operation(summary = "Login Admin API")
	public ResponseEntity<ResponseDto> adminLogin(LoginDto loginDto) {
		log.info("Entered into adminLogin controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.ADMIN_LOGIN_SUCCESS, adminService.login(loginDto)));

	}

	@GetMapping("/leaves")
	@Operation(summary = "Get All Leaves API")
	public ResponseEntity<ResponseDto> getAllLeaves() {
		log.info("Entered into admin getAllLeaves controller"); 
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.ADMIN_GETALL_LEAVE_SUCCESS, adminService.getAllLeaves()));

	} 

	@PostMapping("leaveRequest/approve")
	@Operation(summary = "Approve or Reject leave Request API")
	public ResponseEntity<ResponseDto> approveLeaveRequest(
			@RequestParam @Parameter(description = "approve/reject") String status, @RequestParam Integer id) {
		log.info("Entered into admin approveLeaveRequest"); 
		return ResponseEntity
				.ok(new ResponseDto(false, Constant.LEAVE_REQUEST_STATUS_UPDATED, adminService.approve(status, id)));

	}

}
