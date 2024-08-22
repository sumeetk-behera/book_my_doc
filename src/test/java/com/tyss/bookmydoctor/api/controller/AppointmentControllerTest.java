package com.tyss.bookmydoctor.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tyss.bookmydoctor.api.dto.AppointmentDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.service.AppointmentService;

@SpringBootTest
class AppointmentControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AppointmentService appointmentService;

	@InjectMocks
	private AppointmentController controller;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	AppointmentDto appointmentDto = new AppointmentDto(1, "Sumeet", 25, 987456321L, "s@gmail.com", "Dr Rahul", null,
			null, null, null, null);
	List<AppointmentDto> dtos = List.of(appointmentDto);

	@Test
	void bookAppointment_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(appointmentService.book(any())).thenReturn(appointmentDto);
		String contentAsString = mockMvc
				.perform(
						post("/api/v1//book").accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)
								.content(objectMapper.writeValueAsString(appointmentDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Your Appointment Booked Successfully", readValue.getMsg());
	}

	@Test
	void getAllAppointments_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(appointmentService.getAll()).thenReturn(dtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1/appointments").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dtos)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Appointments Found Successfully", readValue.getMsg());
	}

	@Test
	void get_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(appointmentService.get(anyInt())).thenReturn(dtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1/appointment/1").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dtos)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Appointment Found Successfully", readValue.getMsg());
	}

}
