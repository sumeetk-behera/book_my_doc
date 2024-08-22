package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class SpecialistTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"id\":0,\"speciality\":\"medicine\",\"doctorsList\":null}";

	@Test
	void serializeTestForSpecialist() throws JsonProcessingException {
		Specialist specialist = new Specialist();
		specialist.setId(0);
		specialist.setSpeciality("abc");
//		String writeValueAsString = objectMapper.writeValueAsString(specialist);
//		System.err.println(writeValueAsString);
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, Specialist.class));
		assertThat(writeValueAsString).isEqualTo(json);

	}

	@Test
	void deserializeTestForSpecialist() throws JsonMappingException, JsonProcessingException {
		Specialist readValue = objectMapper.readValue(json, Specialist.class);
		assertEquals("medicine", readValue.getSpeciality());
	}

}
