package com.tyss.bookmydoctor.api.service;

import java.util.List;

import com.tyss.bookmydoctor.api.dto.DoctorDto;
import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.SpecialistDto;

public interface DoctorService {

	DoctorDto register(DoctorDto doctorDto);

	List<DoctorDto> getAll();

	List<DoctorDto> search(String location, String speciality);

	SpecialistDto saveSpecialist(SpecialistDto specialistDto);

	DoctorDto login(LoginDto loginDto);

	LeaveRequestDto leaveRequest(LeaveRequestDto requestDto);
}
