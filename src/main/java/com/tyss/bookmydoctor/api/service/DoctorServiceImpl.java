package com.tyss.bookmydoctor.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.constant.ExceptionConstant;
import com.tyss.bookmydoctor.api.dto.DoctorDto;
import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.SpecialistDto;
import com.tyss.bookmydoctor.api.entity.Doctor;
import com.tyss.bookmydoctor.api.entity.LeaveRequest;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.entity.Specialist;
import com.tyss.bookmydoctor.api.exception.DoctorNotFoundException;
import com.tyss.bookmydoctor.api.exception.DoctorPresentException;
import com.tyss.bookmydoctor.api.exception.LeaveRequestFailedException;
import com.tyss.bookmydoctor.api.exception.LoginFailedException;
import com.tyss.bookmydoctor.api.exception.SpecialistAlreadyPresentException;
import com.tyss.bookmydoctor.api.repository.DoctorRepository;
import com.tyss.bookmydoctor.api.repository.RoleRepository;
import com.tyss.bookmydoctor.api.repository.SpecialistRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	SpecialistRepository specialistRepository;

	@Override
	public DoctorDto register(DoctorDto doctorDto) {

		log.info("Entered into Doctor register service");

		Optional<Doctor> findByName = doctorRepository.findByName(doctorDto.getName());
		Optional<Role> findByRoleName = roleRepository.findByRoleName(doctorDto.getRoleName());
		Optional<Specialist> findBySpeciality = specialistRepository.findBySpeciality(doctorDto.getSpeciality());
		List<Doctor> list = new ArrayList<>(); 

		if (findByName.isEmpty() && findByRoleName.isPresent() && findBySpeciality.isPresent()) {

			Role role = findByRoleName.get();
			log.info(Constant.FETCHED, role);

			Specialist specialist = findBySpeciality.get();
			log.info(Constant.FETCHED, specialist);

			Doctor doctor = new Doctor();
			BeanUtils.copyProperties(doctorDto, doctor);
			list.add(doctor);
			role.setDoctorsList(list);
			specialist.setDoctorsList(list);
			List<Doctor> doctorsList = role.getDoctorsList();
			doctorsList.forEach(doc -> doc.setRole(role));
			doctorsList.forEach(docs -> docs.setSpecialist(specialist));
			log.info("doctorsList : {} ", doctorsList);
			doctorRepository.saveAll(doctorsList);
			BeanUtils.copyProperties(doctor, doctorDto);
			return doctorDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new DoctorPresentException(ExceptionConstant.DOCTOR_FOUND);
	}

	@Override
	public List<DoctorDto> getAll() {

		log.info("Entered into Doctor getAll service");

		List<Doctor> findAll = doctorRepository.findAll();
		log.info(Constant.FETCHED, findAll);

		List<DoctorDto> dtosList = new ArrayList<>();
		if (!findAll.isEmpty()) {
			findAll.forEach(doc -> {
				DoctorDto dto = new DoctorDto();
				BeanUtils.copyProperties(doc, dto);
				dtosList.add(dto);
			});
			log.info("dtosList : {} ", dtosList);
			return dtosList;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new DoctorNotFoundException(ExceptionConstant.DOCTOR_NOT_FOUND);
	}

	@Override
	public List<DoctorDto> search(String location, String speciality) {

		log.info("Entered into Doctor search service");

		Optional<List<Doctor>> findByLocationOrSpeciality = doctorRepository.findByLocationOrSpeciality(location,
				speciality);
 
		List<DoctorDto> dtoList = new ArrayList<>();

		if (findByLocationOrSpeciality.isPresent()) {
			List<Doctor> list = findByLocationOrSpeciality.get();
			log.info(Constant.FETCHED, list);

			list.forEach(doctor -> {
				DoctorDto dto = new DoctorDto();
				BeanUtils.copyProperties(doctor, dto);
				dtoList.add(dto);
			});
			log.info("dtosList : {} ", dtoList);
			return dtoList;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new DoctorNotFoundException(ExceptionConstant.DOCTOR_NOT_FOUND);
	}

	@Override
	public SpecialistDto saveSpecialist(SpecialistDto specialistDto) {
		log.info("Entered into Doctor saveSpeciality service");
		Optional<Specialist> findBySpecility = specialistRepository.findBySpeciality(specialistDto.getSpeciality());
		if (findBySpecility.isEmpty()) {
			Specialist specialist = new Specialist();
			BeanUtils.copyProperties(specialistDto, specialist);
			log.info("specialist : {} ", specialist);
			Specialist save = specialistRepository.save(specialist);
			BeanUtils.copyProperties(save, specialistDto);
			return specialistDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new SpecialistAlreadyPresentException(ExceptionConstant.SPECIALITY_FOUND);
	}

	@Override
	public DoctorDto login(LoginDto loginDto) {
		log.info("Entered into Doctor login service");
		Optional<Doctor> findByEmail = doctorRepository.findByEmailAndPassword(loginDto.getEmail(),
				loginDto.getPassword());
		if (findByEmail.isPresent()) {
			log.info(Constant.FETCHED, findByEmail.get());
			System.err.println(findByEmail+" findByEmail");
			DoctorDto doctorDto = new DoctorDto();
			BeanUtils.copyProperties(findByEmail.get(), doctorDto);
			log.info("doctorDto : {} ", doctorDto);
			return doctorDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LoginFailedException(ExceptionConstant.LOGIN_FAILED);
	}

	@Override
	public LeaveRequestDto leaveRequest(LeaveRequestDto requestDto) {
		log.info("Entered into Doctor leaveRequest service");
		Optional<Doctor> findByName = doctorRepository.findByName(requestDto.getDoctorName());

		List<LeaveRequest> list = new ArrayList<>();
		if (findByName.isPresent()) {
			Doctor doctor = findByName.get();
			log.info(Constant.FETCHED, doctor);

			LeaveRequest leaveRequest = new LeaveRequest();
			BeanUtils.copyProperties(requestDto, leaveRequest);
			leaveRequest.setStatus("pending");
			list.add(leaveRequest);
			doctor.setLeaveRequests(list);

			List<LeaveRequest> leaveRequests = doctor.getLeaveRequests();
			leaveRequests.forEach(leave -> leave.setDoctor(doctor));
			log.info("doctor : {} ", doctor);
			doctorRepository.save(doctor);
			BeanUtils.copyProperties(leaveRequest, requestDto);
			return requestDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LeaveRequestFailedException(ExceptionConstant.LEAVE_REQUEST_FAILED);

	}

}
