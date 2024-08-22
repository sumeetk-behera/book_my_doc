package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class UserTest {

	String json = "{\"id\":0,\"name\":null,\"age\":0,\"email\":null,\"password\":null,\"sex\":null,\"phoneNumber\":0,\"roleName\":null,\"role\":null,\"appointmentsList\":null}";

	ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void serializeTestForUser() throws JsonMappingException, JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, User.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}
}
