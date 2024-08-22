package com.tyss.bookmydoctor.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tyss.bookmydoctor.api.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@JsonIgnore
	private int id;

	@NotBlank
	private String name;

	@NotBlank
	private int age;

	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private String sex;

	@Size(min = 10)
	private long phoneNumber;

	@NotBlank
	@Pattern(regexp = "Role_User")
	private String roleName;

	@JsonIgnore
	private Role role;

//	@JsonIgnore
//	private Set<RoleDto> roleDtosList = new HashSet<>();

}
