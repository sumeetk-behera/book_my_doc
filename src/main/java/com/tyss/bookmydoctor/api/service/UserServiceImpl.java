package com.tyss.bookmydoctor.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.constant.ExceptionConstant;
import com.tyss.bookmydoctor.api.dto.LoginDto;
import com.tyss.bookmydoctor.api.dto.UserDto;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.entity.User;
import com.tyss.bookmydoctor.api.exception.LoginFailedException;
import com.tyss.bookmydoctor.api.exception.UserFoundException;
import com.tyss.bookmydoctor.api.repository.RoleRepository;
import com.tyss.bookmydoctor.api.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDto saveUser(UserDto userDto) {
		log.info("Entered into User saveUser service");
		Optional<User> findByEmail = userRepository.findByEmail(userDto.getEmail());
		Optional<Role> findByRoleName = roleRepository.findByRoleName(userDto.getRoleName());
		List<User> list = new ArrayList<>();
		if (findByEmail.isEmpty() && findByRoleName.isPresent()) {
			Role role = findByRoleName.get();
			log.info(Constant.FETCHED, role);

			User user = new User();
			BeanUtils.copyProperties(userDto, user);
			list.add(user);
			role.setUsersList(list);
			List<User> usersList = role.getUsersList();
			usersList.forEach(users -> users.setRole(role));
			log.info("usersList : {} ", usersList);
			userRepository.saveAll(usersList);
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new UserFoundException(ExceptionConstant.USER_FOUND);

		// Optimized Way of writing code
//		return userRepository.findByEmail(userDto.getEmail()).filter(Objects::isNull).map(x -> {
//			User user = new User();
//			BeanUtils.copyProperties(userDto, user);
//			User save = userRepository.save(user);
//			BeanUtils.copyProperties(save, userDto);
//			return userDto;
//		}).orElseThrow(()-> new UserFoundException("User Already present, please login"));
	}

	@Override
	public UserDto login(LoginDto loginDto) {
		log.info("Entered into User login service");
		Optional<User> findByEmailAndPassword = userRepository.findByEmailAndPassword(loginDto.getEmail(),
				loginDto.getPassword());

		if (findByEmailAndPassword.isPresent()) {
//			log.info(Constant.FETCHED, findByEmailAndPassword.get());

			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(loginDto, userDto);
//			log.info("userDto : {} ", userDto);
			return userDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LoginFailedException(ExceptionConstant.LOGIN_FAILED);
	}

}
