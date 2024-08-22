package com.tyss.bookmydoctor.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.bookmydoctor.api.constant.Constant;
import com.tyss.bookmydoctor.api.constant.ExceptionConstant;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LeaveRequestRepository leaveRepository;

	@Override
	public UserDto login(LoginDto loginDto) {
		log.info("Entered into User login service");
		Optional<User> findByEmail = userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());

		if (findByEmail.isPresent()) {
			log.info(Constant.FETCHED, findByEmail.get());
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(loginDto, userDto);
			log.info("userDto : {} ", userDto);
			return userDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LoginFailedException(ExceptionConstant.LOGIN_FAILED);
	}

	@Override
	public List<LeaveRequestDto> getAllLeaves() {
		log.info("Entered into getAllLeaves service");
		List<LeaveRequest> findAll = leaveRepository.findAll();
		log.info(Constant.FETCHED, findAll);
		List<LeaveRequestDto> leaveDtoList = new ArrayList<>();
		if (!findAll.isEmpty()) {
			findAll.forEach(leave -> {
				LeaveRequestDto requestDto = new LeaveRequestDto();
				BeanUtils.copyProperties(leave, requestDto);
				leaveDtoList.add(requestDto);
			});
			log.info("leaveDtoList : {} ", leaveDtoList);
			return leaveDtoList;
		}

		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LeaveRequestNotFoundException(ExceptionConstant.NO_LEAVE_REQUEST);

	}

	@Override
	public LeaveRequestDto approve(String status, Integer id) {
		log.info("Entered into approve service");
		Optional<LeaveRequest> findById = leaveRepository.findByIdAndStatus(id,status);
		if (findById.isPresent()) {
			log.info(Constant.FETCHED, findById.get()); 
			findById.get().setStatus(status);
			LeaveRequest save = leaveRepository.save(findById.get());
			LeaveRequestDto dto = new LeaveRequestDto();
			BeanUtils.copyProperties(save, dto);
			log.info("dto : {} ", dto);
			return dto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new LeaveRequestAlreadyApproved(ExceptionConstant.LEAVE_REQUEST_STATUS);
	}

}
