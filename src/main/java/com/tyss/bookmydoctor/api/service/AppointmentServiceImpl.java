package com.tyss.bookmydoctor.api.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.constant.ExceptionConstant;
import com.tyss.bookmydoctor.api.dto.AppointmentDto;
import com.tyss.bookmydoctor.api.entity.Appointment;
import com.tyss.bookmydoctor.api.entity.Doctor;
import com.tyss.bookmydoctor.api.entity.User;
import com.tyss.bookmydoctor.api.exception.AppointmentNotBookedException;
import com.tyss.bookmydoctor.api.exception.NoAppointmentsFoundException;
import com.tyss.bookmydoctor.api.repository.AppointmentRepository;
import com.tyss.bookmydoctor.api.repository.DoctorRepository;
import com.tyss.bookmydoctor.api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AppointmentServiceImpl implements AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public AppointmentDto book(AppointmentDto appointmentDto) {
		log.info("Entered into Appointment book service");
		Optional<Doctor> findByName = doctorRepository.findByName(appointmentDto.getDoctorName());
		Optional<User> findByEmail = userRepository.findByEmail(appointmentDto.getEmail());
		List<Appointment> appointmentsList = new ArrayList<>();
		if (findByName.isPresent() && findByEmail.isPresent()) {
			Doctor doctor = findByName.get();
			log.info(Constant.FETCHED, doctor); 
			User user = findByEmail.get();
			log.info(Constant.FETCHED, user);
			Appointment appointment = new Appointment();
			BeanUtils.copyProperties(appointmentDto, appointment);
			appointmentsList.add(appointment);
			doctor.setAppointmentsList(appointmentsList);
			user.setAppointmentsList(appointmentsList);

			List<Appointment> appointmentsList2 = doctor.getAppointmentsList();
			appointmentsList2.forEach(app -> {
				app.setDoctor(doctor);
				app.setUser(user);
			});
			log.info("appointmentsList2 : {} ", appointmentsList2);
			appointmentRepository.saveAll(appointmentsList2);
			BeanUtils.copyProperties(appointment, appointmentDto);

			return appointmentDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new AppointmentNotBookedException(ExceptionConstant.APPOINTMENT_BOOK_FAILED);

	}

	@Override
	public List<AppointmentDto> getAll() {
		log.info("Entered into Appointment getAll service");
		List<Appointment> findAll = appointmentRepository.findAll();
		List<AppointmentDto> dtoList = new ArrayList<>();
		if (!findAll.isEmpty()) {
			findAll.forEach(appointment -> {
				AppointmentDto dto = new AppointmentDto();
				BeanUtils.copyProperties(appointment, dto);
				dtoList.add(dto);
				dtoList.sort(Comparator.comparing(AppointmentDto::getAppointmentDate));
			});
			log.info("dtoList : {} ", dtoList);
			return dtoList;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new NoAppointmentsFoundException(ExceptionConstant.APPOINTMENT_NOT_FOUND);
	}

	@Override
	public List<AppointmentDto> get(Integer id) {
		log.info("Entered into Appointment get service");
		List<Appointment> findByDoctorId = appointmentRepository.findByDoctorId(id);
		log.info(Constant.FETCHED, findByDoctorId);
		List<AppointmentDto> dtoList = new ArrayList<>();
		if (!findByDoctorId.isEmpty()) {
			findByDoctorId.forEach(appointment -> {
				AppointmentDto dto = new AppointmentDto();
				BeanUtils.copyProperties(appointment, dto);
				dtoList.add(dto);
				dtoList.sort(Comparator.comparing(AppointmentDto::getAppointmentDate));
			});
			log.info("dtoList : {} ", dtoList);
			return dtoList;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new NoAppointmentsFoundException(ExceptionConstant.APPOINTMENT_NOT_FOUND);
	}

}
