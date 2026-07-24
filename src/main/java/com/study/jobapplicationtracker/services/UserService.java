package com.study.jobapplicationtracker.services;

import com.study.jobapplicationtracker.dtos.UserDto;
import com.study.jobapplicationtracker.enums.Role;

public interface UserService {

	UserDto registerUser(UserDto userDto, Role role);
	
	boolean checkEmail(String email);
}
