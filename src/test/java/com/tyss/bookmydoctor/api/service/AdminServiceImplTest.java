package com.tyss.bookmydoctor.api.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tyss.bookmydoctor.api.dto.LeaveRequestDto;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.entity.LeaveRequest;
import com.tyss.bookmydoctor.api.entity.User;
import com.tyss.bookmydoctor.api.exception.LeaveRequestAlreadyApproved;
import com.tyss.bookmydoctor.api.exception.LeaveRequestNotFoundException;
import com.tyss.bookmydoctor.api.exception.LoginFailedException;
import com.tyss.bookmydoctor.api.repository.LeaveRequestRepository;
import com.tyss.bookmydoctor.api.repository.UserRepository;

@SpringBootTest
class AdminServiceImplTest {

	@Mock
	private UserRepository repository;

	@Mock
	private LeaveRequestRepository leaveReposiotry;

	@InjectMocks
	private AdminServiceImpl service;

	UserDto userDto = new UserDto(1, "Sumeet", 25, "s@gmail.com", "123", "male", 987456321L, "Role_User", null);

	LeaveRequest request = new LeaveRequest(1, null, null, "marriage", "approved", null);
	List<LeaveRequest> list = List.of(request);
	LeaveRequestDto dto = new LeaveRequestDto(1, null, null, "marriage", "rejected", null);
	List<LeaveRequestDto> dtos = List.of(dto);

	LoginDto loginDto = new LoginDto("s@gmail.com", "123");

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

	@Test
	void getAllLeaves_success() {
		when(leaveReposiotry.findAll()).thenReturn(list);
		List<LeaveRequestDto> dtos = service.getAllLeaves();
		assertEquals("marriage", dtos.get(0).getReason());
	}

	@Test
	void getAllLeaves_fail() {
		List<LeaveRequest> list = new ArrayList<>();
		when(leaveReposiotry.findAll()).thenReturn(list);
		assertThatThrownBy(() -> service.getAllLeaves()).isInstanceOf(LeaveRequestNotFoundException.class);
	}

	@Test
	void approve_success() {
		Optional<LeaveRequest> optional = Optional.of(new LeaveRequest());
		when(leaveReposiotry.findByIdAndStatus(anyInt(), anyString())).thenReturn(optional);
		optional.get().setStatus("approved");
		when(leaveReposiotry.save(optional.get())).thenReturn(request);
		LeaveRequestDto dto = service.approve("approved", 1);
		assertEquals("approved", dto.getStatus());
	}

	@Test
	void approve_fail() {
		Optional<LeaveRequest> optional = Optional.empty();
		when(leaveReposiotry.findByIdAndStatus(anyInt(), anyString())).thenReturn(optional);
		assertThatThrownBy(() -> service.approve("approve", 1)).isInstanceOf(LeaveRequestAlreadyApproved.class);
	}
}
