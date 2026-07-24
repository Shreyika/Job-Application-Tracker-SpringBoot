package com.study.jobapplicationtracker.services.impl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.study.jobapplicationtracker.dtos.UserDto;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.enums.Role;
import com.study.jobapplicationtracker.repositories.UserRepository;
import com.study.jobapplicationtracker.services.UserService;

@Service
public class UserServiceImpl implements UserService{

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;


	UserServiceImpl(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public UserDto registerUser(UserDto userDto, Role role) {
		
		String hashedPassword = passwordEncoder.encode(userDto.getPassword());
		
		userDto.setPassword(hashedPassword);

		User user = modelMapper.map(userDto, User.class);
		  // Set role
	    user.setRole(role);
		User savedUser = userRepository.save(user);
		return modelMapper.map(savedUser,UserDto.class);
	}

	@Override
	public boolean checkEmail(String email) {
		return userRepository.existsByEmail(email);
		
	}

}
