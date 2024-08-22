package com.tyss.bookmydoctor.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.bookmydoctor.api.dto.RoleDto;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.exception.RoleFoundException;
import com.tyss.bookmydoctor.api.repository.RoleRepository;

@SpringBootTest
class RoleServiceImplTest {

	@InjectMocks
	private RoleServiceImpl service;

	@Mock
	private RoleRepository repository;

	Role role = new Role(1, "Role_User", null, null); 

	RoleDto roleDto = new RoleDto(1, "Role_User");

	@Test
	void saveRole_success() {
		Optional<Role> optional = Optional.empty();
		when(repository.findByRoleName(anyString())).thenReturn(optional);
		when(repository.save(any())).thenReturn(role);
		RoleDto saveRole = service.saveRole(roleDto);
		assertEquals(1, saveRole.getId());
	}

	@Test
	void saveRole_fail() {
		Optional<Role> optional = Optional.of(new Role());
		when(repository.findByRoleName(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.saveRole(roleDto)).isInstanceOf(RoleFoundException.class);
	}
} 
