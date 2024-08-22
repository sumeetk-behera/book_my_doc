package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class AppointmentTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"id\":0,\"patientName\":\"sumeet\",\"age\":25,\"phoneNumber\":987456321,\"email\":\"s@gmail.com\",\"doctorName\":\"Rahul\",\"createdDate\":null,\"appointmentDate\":null,\"time\":null,\"doctor\":null,\"user\":null}";

	@Test
	void serializeTestForAppointment() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, Appointment.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForAppointment() throws JsonMappingException, JsonProcessingException {
		Appointment readValue = objectMapper.readValue(json, Appointment.class);
		assertEquals("sumeet", readValue.getPatientName());
	}
}
