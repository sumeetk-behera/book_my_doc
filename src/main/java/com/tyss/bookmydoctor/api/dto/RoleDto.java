package com.tyss.bookmydoctor.api.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

	@JsonIgnore
	private int id;

	@NotBlank
	private String roleName;
	
	
}
