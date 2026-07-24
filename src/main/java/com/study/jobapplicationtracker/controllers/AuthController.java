package com.study.jobapplicationtracker.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jobapplicationtracker.dtos.LoginRequest;
import com.study.jobapplicationtracker.dtos.LoginResponse;
import com.study.jobapplicationtracker.dtos.UserDto;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.jwt.JwtUtils;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest)
	{
		Authentication authentication=null;
		try
		{
		authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		}catch(AuthenticationException e)
		{
			System.out.println("Bad credentials"+e.getMessage());
		}
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//typecasting
		User user=(User)authentication.getPrincipal();
		
		String token = jwtUtils.generateTokenFromUsername(user);
		
		UserDto dto = modelMapper.map(user, UserDto.class);
		
		LoginResponse response = new LoginResponse(token, dto);

		
		return ResponseEntity.ok(response);
	}
}
