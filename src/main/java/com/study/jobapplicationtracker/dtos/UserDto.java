package com.study.jobapplicationtracker.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class UserDto {

	private String id;
	
	@Size(min = 2, max = 60)
	@NotNull(message = "firstname can't be null")
	@NotBlank(message = "firstname can't be blank")
	private String firstName;
	
	@NotNull(message = "firstname can't be null")
	@NotBlank(message = "firstname can't be blank")
	private String lastName;
	
	@Pattern(regexp = "^((?!\\.)[\\w\\-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$")
	private String email;
	
	@Pattern(regexp = "^((?=\\S*?[A-Z])(?=\\S*?[a-z])(?=\\S*?[0-9]).{6,})\\S$")
	private String password;
	
	private String confirmPassword;
}
