package com.tyss.bookmydoctor.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.entity.Specialist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorDto {

	@JsonIgnore
	private int id;

	@NotBlank
	@Pattern(regexp = "Dr")
	private String name;

	@NotBlank
	private int age;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String sex;

	@NotBlank
	@Size(min = 10)
	private long phoneNumber;

	private String ratings;

	@NotBlank
	private String location;

	@NotBlank
	@Pattern(regexp = "Role_Doctor")
	private String roleName;

	@NotBlank
	private String speciality;

	@JsonIgnore
	private Role role;

	@JsonIgnore
	private Specialist specialist;

}
