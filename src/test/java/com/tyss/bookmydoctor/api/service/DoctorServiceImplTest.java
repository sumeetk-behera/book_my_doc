package com.tyss.bookmydoctor.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

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

@SpringBootTest
class DoctorServiceImplTest {

	@InjectMocks
	private DoctorServiceImpl service;

	@Mock
	private DoctorRepository doctorRepository;

	@Mock
	private RoleRepository roleRepository;

	@Mock
	private SpecialistRepository specialistRepository;

	Doctor doctor = new Doctor(1, "sumeet", 25, "s@gmail.com", "123", "male", 987456321L, null, "Bengaluru", "medicine",
			"Role_Doctor", null, null, null, null);
	List<Doctor> list = List.of(doctor);

	DoctorDto doctorDto = new DoctorDto(1, "sumeet", 25, "s@gmail.com", "123", "male", 987456321L, null, "Bangaluru",
			"Role_Doctor", "Medicine", null, null);
	List<DoctorDto> dtos = List.of(doctorDto);

	Specialist specialist = new Specialist(1, "medicine", list);

	SpecialistDto dto = new SpecialistDto(1, "medicine");

	LoginDto loginDto = new LoginDto("s@gmail.com", "123");

	LeaveRequest leaveRequest = new LeaveRequest(1, null, null, "marriage", "approved", null);
	List<LeaveRequest> requests = List.of(leaveRequest);
	LeaveRequestDto requestDto = new LeaveRequestDto(1, null, null, "marriage", "approve", "sumeet");

	@Test
	void register_success() {
		Optional<Doctor> optional = Optional.empty();
		Optional<Role> optionalRole = Optional.of(new Role());
		Optional<Specialist> optionalSpeciality = Optional.of(new Specialist());
		when(doctorRepository.findByName(anyString())).thenReturn(optional);
		when(roleRepository.findByRoleName(anyString())).thenReturn(optionalRole);
		when(specialistRepository.findBySpeciality(anyString())).thenReturn(optionalSpeciality);
		optionalRole.get().setDoctorsList(list);
		optionalSpeciality.get().setDoctorsList(list);
		List<Doctor> doctorsList = optionalRole.get().getDoctorsList();
		doctorsList.get(0).setRole(optionalRole.get());
		doctorsList.get(0).setSpecialist(optionalSpeciality.get());
		when(doctorRepository.saveAll(doctorsList)).thenReturn(doctorsList);
		DoctorDto register = service.register(doctorDto);
		assertEquals("s@gmail.com", register.getEmail());
	}

	@Test
	void register_fail() {
		Optional<Doctor> optional = Optional.of(new Doctor());
		when(doctorRepository.findByName(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.register(doctorDto)).isInstanceOf(DoctorPresentException.class);
	}

	@Test
	void getAll_success() {
		when(doctorRepository.findAll()).thenReturn(list);
		List<DoctorDto> dtos = service.getAll();
		assertEquals("sumeet", dtos.get(0).getName());
	}

	@Test
	void getAll_fail() {
		List<Doctor> doctors = new ArrayList<>();
		when(doctorRepository.findAll()).thenReturn(doctors);
		assertThatThrownBy(() -> service.getAll()).isInstanceOf(DoctorNotFoundException.class);
	}

	@Test
	void search_success() {
		Optional<List<Doctor>> optional = Optional.of(list);
		when(doctorRepository.findByLocationOrSpeciality(anyString(), anyString())).thenReturn(optional);
		List<DoctorDto> dtos = service.search("Bengaluru", "medicine");
		assertEquals("sumeet", dtos.get(0).getName());
	}

	@Test
	void search_fail() {
		Optional<List<Doctor>> optional = Optional.empty();
		when(doctorRepository.findByLocationOrSpeciality(anyString(), anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.search("Bengaluru", "medicine")).isInstanceOf(DoctorNotFoundException.class);
	}

	@Test
	void saveSpecialist_success() {
		Optional<Specialist> optional = Optional.empty();
		when(specialistRepository.findBySpeciality(anyString())).thenReturn(optional);
		when(specialistRepository.save(any())).thenReturn(specialist);
		SpecialistDto saveSpecialist = service.saveSpecialist(dto);
		assertEquals("medicine", saveSpecialist.getSpeciality());
	}

	@Test
	void saveSpecialist_fail() {
		Optional<Specialist> optional = Optional.of(new Specialist());
		when(specialistRepository.findBySpeciality(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.saveSpecialist(dto)).isInstanceOf(SpecialistAlreadyPresentException.class);
	}

	@Test
	void login_success() {
		Optional<Doctor> optional = Optional.of(new Doctor(0, null, 0, null, "123", null, 0, null, null, null, null, null, null, specialist, requests));
		when(doctorRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(optional);
		DoctorDto login = service.login(loginDto);
		assertEquals("123", login.getPassword());
	}

	@Test
	void login_fail() {
		Optional<Doctor> optional = Optional.empty();
		when(doctorRepository.findByEmailAndPassword(anyString(), anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.login(loginDto)).isInstanceOf(LoginFailedException.class);
	}

	@Test
	void leaverequest_success() {
		Optional<Doctor> optional = Optional.of(new Doctor());
		when(doctorRepository.findByName(anyString())).thenReturn(optional);
		optional.get().setLeaveRequests(requests);
		List<LeaveRequest> leaveRequests = optional.get().getLeaveRequests();
		leaveRequests.get(0).setDoctor(optional.get());
		when(doctorRepository.save(any())).thenReturn(optional.get());
		LeaveRequestDto leaveRequest2 = service.leaveRequest(requestDto);
		assertEquals("marriage", leaveRequest2.getReason());
	}

	@Test
	void leaveRequest_fail() {
		Optional<Doctor> optional = Optional.empty();
		when(doctorRepository.findByName(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.leaveRequest(requestDto)).isInstanceOf(LeaveRequestFailedException.class);
	}
}
