package com.tyss.bookmydoctor.api.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tyss.bookmydoctor.api.constant.ExceptionConstant;
import com.tyss.bookmydoctor.api.dto.RoleDto;
import com.tyss.bookmydoctor.api.entity.Role;
import com.tyss.bookmydoctor.api.exception.RoleFoundException;
import com.tyss.bookmydoctor.api.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public RoleDto saveRole(RoleDto roleDto) {
		log.info("Entered into Role saveRole service");
		Optional<Role> findByRoleName = roleRepository.findByRoleName(roleDto.getRoleName());
		if (findByRoleName.isEmpty()) {
			Role role = new Role();
			BeanUtils.copyProperties(roleDto, role);
			Role save = roleRepository.save(role);
			BeanUtils.copyProperties(save, roleDto); 
			return roleDto;
		}
		log.info(ExceptionConstant.EXCEPTION_RAISED);
		throw new RoleFoundException(ExceptionConstant.ROLE_FOUND);
	}

}
