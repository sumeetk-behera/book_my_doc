package com.tyss.bookmydoctor.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.bookmydoctor.api.dto.AppointmentDto;
import com.tyss.bookmydoctor.api.entity.Appointment;
import com.tyss.bookmydoctor.api.entity.Doctor;
import com.tyss.bookmydoctor.api.entity.User;
import com.tyss.bookmydoctor.api.exception.AppointmentNotBookedException;
import com.tyss.bookmydoctor.api.exception.NoAppointmentsFoundException;
import com.tyss.bookmydoctor.api.repository.AppointmentRepository;
import com.tyss.bookmydoctor.api.repository.DoctorRepository;
import com.tyss.bookmydoctor.api.repository.UserRepository;

@SpringBootTest
class AppointmentServiceImplTest {

	@Mock
	private DoctorRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private AppointmentRepository appointmentRepository;

	@InjectMocks
	private AppointmentServiceImpl service;

	Appointment appointment = new Appointment(1, "sumeet", 25, 987456321L, "s@gmail.com", "rahul", null, null, null,
			null, null);
	List<Appointment> list = List.of(appointment);

	AppointmentDto appointmentDto = new AppointmentDto(1, "sumeet", 25, 987456321L, "s@gmil.com", "rahul", null, null,
			null, null, null);
	List<AppointmentDto> dtos = List.of(appointmentDto);

	@Test
	void bookAppointment_success() {
		Optional<Doctor> optional = Optional.of(new Doctor());
		Optional<User> optionalUser = Optional.of(new User());
		when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);
		when(repository.findByName(anyString())).thenReturn(optional);
		optional.get().setAppointmentsList(list);
		optionalUser.get().setAppointmentsList(list);
		List<Appointment> appointmentsList = optional.get().getAppointmentsList();
		appointmentsList.get(0).setDoctor(optional.get());
		appointmentsList.get(0).setUser(optionalUser.get());
		when(appointmentRepository.saveAll(anyList())).thenReturn(appointmentsList);
		AppointmentDto book = service.book(appointmentDto);
		assertEquals("sumeet", book.getPatientName());
	}

	@Test
	void bookAppointment_fail() {
		Optional<Doctor> optionalDoc = Optional.empty();
		Optional<User> optional = Optional.empty();
		when(repository.findByName(anyString())).thenReturn(optionalDoc);
		when(userRepository.findByEmail(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.book(appointmentDto)).isInstanceOf(AppointmentNotBookedException.class);

	}

	@Test
	void getAll_success() {
		when(appointmentRepository.findAll()).thenReturn(list);
		List<AppointmentDto> dtos = service.getAll();
		assertEquals("sumeet", dtos.get(0).getPatientName());
	}

	@Test
	void getAll_fail() {
		List<Appointment> appointments = new ArrayList<>();
		when(appointmentRepository.findAll()).thenReturn(appointments);
		assertThatThrownBy(() -> service.getAll()).isInstanceOf(NoAppointmentsFoundException.class);
	}

	@Test
	void get_success() {
		when(appointmentRepository.findByDoctorId(anyInt())).thenReturn(list);
		List<AppointmentDto> dtos = service.get(anyInt());
		assertEquals("sumeet", dtos.get(0).getPatientName());
	}

	@Test
	void get_fail() {
		List<Appointment> appointments = new ArrayList<>();
		when(appointmentRepository.findByDoctorId(anyInt())).thenReturn(appointments);
		assertThatThrownBy(() -> service.get(1)).isInstanceOf(NoAppointmentsFoundException.class);
	}

}
