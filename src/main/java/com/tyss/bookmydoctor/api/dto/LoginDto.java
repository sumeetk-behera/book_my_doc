package com.tyss.bookmydoctor.api.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

	@NotBlank
	private String email;

	@NotBlank
	private String password;

}
