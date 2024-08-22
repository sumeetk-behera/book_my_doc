package com.tyss.bookmydoctor.api.service;

import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.UserDto;

public interface UserService {

	UserDto saveUser(UserDto userDto);

	UserDto login(LoginDto loginDto);

//	RoleDto saveRole(RoleDto roleDto);

//	AssignRoleDto addRoleToUser(AssignRoleDto assignRoleDto);

//	AssignSpecialistDto addSpecialistToUser(AssignSpecialistDto assignSpecialistDto);

}
