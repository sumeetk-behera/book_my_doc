package com.tyss.bookmydoctor.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.dto.RoleDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.service.RoleService;
import com.tyss.bookmydoctor.api.service.UserService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@RestController
@OpenAPIDefinition(info = @Info(title = "BOOK MY DOCTOR", description = "FIND YOUR DOCTOR AND BOOK APPOINTMENT", version = "V1"))
@RequestMapping("/api/v1")
@Slf4j
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	private RoleService roleService;

	@PostMapping("/user/register")
	@Operation(summary = "Register User API")
	public ResponseEntity<ResponseDto> saveUser(@RequestBody UserDto userDto) {
		log.info("Entered into User saveUser controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.USER_SAVE_SUCCESS, service.saveUser(userDto)));

	}

	@PostMapping("/add/role") 
	@Operation(summary = "Save Roles For User API")
	public ResponseEntity<ResponseDto> saveRole(@RequestBody RoleDto roleDto) {
		log.info("Entered into User saveRole controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.ROLE_SAVE_SUCCESS, roleService.saveRole(roleDto)));

	}

	@PostMapping("/login")
	@Operation(summary = "Login User API")
	public ResponseEntity<ResponseDto> userLogin(@RequestBody LoginDto loginDto) {
		log.info("Entered into User login controller");
		return ResponseEntity.ok(new ResponseDto(false, Constant.USER_LOGIN_SUCCESS, service.login(loginDto)));
	}

}
