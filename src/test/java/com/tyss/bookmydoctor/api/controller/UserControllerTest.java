package com.tyss.bookmydoctor.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

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
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.ResponseDto;
import com.tyss.bookmydoctor.api.dto.RoleDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.service.RoleService;
import com.tyss.bookmydoctor.api.service.UserService;

@SpringBootTest
class UserControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UserService service;

	@Mock
	private RoleService roleService;

	@InjectMocks
	private UserController controller;

	ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	UserDto userDto = new UserDto(1, "Sumeet", 25, "s@gmail.com", "123", "male", 987456321L, null, null);

	RoleDto roleDto = new RoleDto(1, "Role_User");

	LoginDto loginDto = new LoginDto("s@gmail.com", "123"); 

	@Test
	void saveUser_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.saveUser(any())).thenReturn(userDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/user/register").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("User Registered Successfully", readValue.getMsg());
	} 

	@Test
	void saveRole_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(roleService.saveRole(any())).thenReturn(roleDto);
		String contentAsString = mockMvc
				.perform(post("/api/v1/add/role").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(roleDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("Role Saved Successfully", readValue.getMsg());
	}

	@Test
	void userLogin_success() throws JsonProcessingException, UnsupportedEncodingException, Exception {
		when(service.login(any())).thenReturn(userDto);
		String contentAsString = mockMvc
				.perform(get("/api/v1/login").accept(MediaType.APPLICATION_JSON)
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseDto readValue = objectMapper.readValue(contentAsString, ResponseDto.class);
		assertEquals("User Logged In Successfully", readValue.getMsg());
	}
}
