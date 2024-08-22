package com.tyss.bookmydoctor.api.service;

import java.util.List;

import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.UserDto;

public interface AdminService {

	UserDto login(LoginDto loginDto);

	List<LeaveRequestDto> getAllLeaves();

	LeaveRequestDto approve(String status, Integer id);

}
