package com.study.jobapplicationtracker.services;

import com.study.jobapplicationtracker.dtos.UserDto;

public interface UserService {

	UserDto registerUser(UserDto userDto);
	
	boolean checkEmail(String email);
}
