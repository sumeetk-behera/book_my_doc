package com.tyss.bookmydoctor.api.service;

import java.util.List;

import com.tyss.bookmydoctor.api.dto.AppointmentDto;

public interface AppointmentService {

	AppointmentDto book(AppointmentDto appointmentDto);

	List<AppointmentDto> getAll();

	List<AppointmentDto> get(Integer id);
}
