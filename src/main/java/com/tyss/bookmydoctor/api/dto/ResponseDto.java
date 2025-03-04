package com.tyss.bookmydoctor.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto {
	private boolean error;
	private String msg;
	private Object object;
}
 