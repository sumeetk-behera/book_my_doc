package com.tyss.bookmydoctor.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.service.AdminService;

@SpringBootTest
class AdminControllerTest {

	private MockMvc mockMvc;

	@Mock
	private AdminService adminService;

	@InjectMocks
	private AdminController adminController;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
	}

	LoginDto loginDto = new LoginDto("s@gmail.com", "123");
	UserDto userDto = new UserDto(1, "Sumeet", 25, "s@gmail.com", "123", "male", 987456321L, null, null);
	LeaveRequestDto requestDto = new LeaveRequestDto(1, null, null, "marriage", "approved", "sumeet");
	List<LeaveRequestDto> dtos = List.of(requestDto);

	@Test
	void adminLogin_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(adminService.login(any())).thenReturn(userDto);
		String contentAsString = mockMvc
				.perform(get("/api/v1/admin/login").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Admin Logged In Successfully", readValue.getMsg());

	}

	@Test
	void GetAllLeaves_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(adminService.getAllLeaves()).thenReturn(dtos);
		String contentAsString = mockMvc
				.perform(get("/api/v1//leaves").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(dtos)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("leaves found Successfully", readValue.getMsg());
	}

	@Test
	void approveLeaveRequest_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(adminService.approve(anyString(), anyInt())).thenReturn(requestDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/leaveRequest/approve").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestDto)).param("id", "1").param("status", "approved"))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
//		assertEquals("Successfully updated status", readValue.getMsg());
		assertThat(readValue.getMsg()).isEqualTo("Successfully updated status");
	}

}
