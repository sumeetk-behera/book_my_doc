package com.tyss.bookmydoctor.api.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
class LeaveRequestTest {

	ObjectMapper objectMapper = new ObjectMapper();

	String json = "{\"id\":0,\"fromDate\":null,\"toDate\":null,\"reason\":\"marriage\",\"status\":\"approved\",\"doctor\":null}";

	@Test
	void serializeTestForLeaveRequest() throws JsonProcessingException {
		String writeValueAsString = objectMapper.writeValueAsString(objectMapper.readValue(json, LeaveRequest.class));
		assertThat(writeValueAsString).isEqualTo(json);
	}

	@Test
	void deserializeTestForLeaveRequest() throws JsonMappingException, JsonProcessingException {
		LeaveRequest readValue = objectMapper.readValue(json, LeaveRequest.class);
		assertEquals("marriage", readValue.getReason());
	}

}
