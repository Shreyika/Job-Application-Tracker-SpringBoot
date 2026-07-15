package com.study.jobapplicationtracker.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.study.jobapplicationtracker.dtos.UserDto;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.repositories.UserRepository;
import com.study.jobapplicationtracker.services.UserService;

public class UserServiceImpl implements UserService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public UserDto registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		
		userRepository.save(user);
		return modelMapper.map(user,UserDto.class);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
		
	}

}
