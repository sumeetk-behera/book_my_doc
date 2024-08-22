package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class RoleTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"id\":0,\"roleName\":\"sumeet\",\"usersList\":null,\"doctorsList\":null}";

	@Test
	void serializeTestForRole() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, Role.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForRole() throws JsonMappingException, JsonProcessingException {
		Role readValue = objectMapper.readValue(json, Role.class);
		assertEquals("sumeet", readValue.getRoleName());
	}

}
