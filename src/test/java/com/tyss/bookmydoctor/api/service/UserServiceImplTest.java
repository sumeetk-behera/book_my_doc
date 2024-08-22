package com.tyss.bookmydoctor.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.entity.User;
import com.tyss.bookmydoctor.api.exception.LoginFailedException;
import com.tyss.bookmydoctor.api.exception.UserFoundException;
import com.tyss.bookmydoctor.api.repository.RoleRepository;
import com.tyss.bookmydoctor.api.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private RoleRepository roleRepository;

	UserDto userDto = new UserDto(1, "Sumeet", 25, "s@gmail.com", "123", "male", 987456321L, "Role_User", null);
	User user = new User(1, "sumeet", 25, "s@gmail.com", "123", "male", 987456321L, "Role_User", null, null);
	List<User> users = List.of(user);
	LoginDto loginDto = new LoginDto("s@gmail.com", "123");
 
	@Test
	void saveUser_success() {
		Optional<User> optionalUser = Optional.empty();
		Optional<Role> optionalRole = Optional.of(new Role());

		when(repository.findByEmail(anyString())).thenReturn(optionalUser);
		when(roleRepository.findByRoleName(anyString())).thenReturn(optionalRole);
		optionalRole.get().setUsersList(users);
		List<User> usersList = optionalRole.get().getUsersList();
		usersList.get(0).setRole(optionalRole.get());
		when(repository.saveAll(usersList)).thenReturn(usersList);
		UserDto saveUser = service.saveUser(userDto);
		assertEquals(1, saveUser.getId());
	}

	@Test
	void saveUser_fail() {
		Optional<User> optional = Optional.of(new User());
		when(repository.findByEmail(anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.saveUser(userDto)).isInstanceOf(UserFoundException.class);
	}

	@Test
	void login_success() {
		Optional<User> optional = Optional.of(new User());
		when(repository.findByEmailAndPassword(anyString(), anyString())).thenReturn(optional);
		UserDto login = service.login(loginDto);
		assertEquals("s@gmail.com", login.getEmail());          
	}

	@Test
	void login_fail() {
		Optional<User> optional = Optional.empty();
		when(repository.findByEmailAndPassword(anyString(), anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.login(loginDto)).isInstanceOf(LoginFailedException.class);
	}

}
