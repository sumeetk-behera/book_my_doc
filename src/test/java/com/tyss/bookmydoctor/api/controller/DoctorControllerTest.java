package com.tyss.bookmydoctor.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.tyss.bookmydoctor.api.dto.DoctorDto;
import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.dto.SpecialistDto;
import com.tyss.bookmydoctor.api.service.DoctorService;

@SpringBootTest
class DoctorControllerTest {

	private MockMvc mockMvc;

	@Mock
	private DoctorService service;

	@InjectMocks
	private DoctorController doctorController;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(doctorController).build();
	}

	DoctorDto doctorDto = new DoctorDto(1, "Sumeet", 25, "s@gmail.com", "123", "male", 987456321L, null, "Bangaluru",
			null, "Medicine", null, null);

	List<DoctorDto> dtos = List.of(doctorDto);

	SpecialistDto specialistDto = new SpecialistDto(1, "Medicine");

	LeaveRequestDto requestDto = new LeaveRequestDto(1, null, null, "Marriage", null, "sumeet");

	@Test
	void registerDoctor_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.register(any())).thenReturn(doctorDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/doctor/register").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctorDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Doctor Registration Successfull", readValue.getMsg());
	}

	@Test
	void doctorLogin_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.login(any())).thenReturn(doctorDto);
		String contentAsString = mockMvc
				.perform(get("/api/v1/doctor/login").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(doctorDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Doctor Logged In Successfully", readValue.getMsg());
	}

	@Test
	void getAll_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.getAll()).thenReturn(dtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1/doctors").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dtos)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Successfully Found All Doctors", readValue.getMsg());
	}

	@Test
	void searchDoctor_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.search(anyString(), anyString())).thenReturn(dtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1/doctor").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dtos)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Found Doctor Successfully", readValue.getMsg());
	}

	@Test 
	void saveSpecialist_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.saveSpecialist(any())).thenReturn(specialistDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/add/specialist").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(specialistDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Specialist added Successfully", readValue.getMsg());
	}

	@Test
	void leaveRequest_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.leaveRequest(any())).thenReturn(requestDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/leaveRequest").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Leave Requested Successfully", readValue.getMsg());
	}

}
