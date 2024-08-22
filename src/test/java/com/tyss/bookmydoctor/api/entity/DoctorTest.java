package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class DoctorTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"id\":0,\"name\":\"sumeet\",\"age\":31,\"email\":\"s@gmail.com\",\"password\":null,\"sex\":null,\"phoneNumber\":0,\"ratings\":null,\"location\":null,\"speciality\":null,\"roleName\":null,\"appointmentsList\":null,\"role\":null,\"specialist\":null,\"leaveRequests\":null}";

	@Test
	void serializeTestForDoctor() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, Doctor.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForDoctor() throws JsonMappingException, JsonProcessingException {
		Doctor readValue = objectMapper.readValue(json, Doctor.class);
		assertEquals("sumeet", readValue.getName());
	}

}
