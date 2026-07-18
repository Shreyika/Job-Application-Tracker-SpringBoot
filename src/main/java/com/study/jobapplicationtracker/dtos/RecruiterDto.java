package com.study.jobapplicationtracker.dtos;

import com.study.jobapplicationtracker.entities.User;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruiterDto {
	
	private Integer recruiterId;
	
	@NotBlank(message = "Company name cannot be blank")
	private String companyName;
	
	@NotNull(message = "Phone Number cannot be null")
	@NotBlank(message = "Phone Number cannot be blank")
	private String phNo; 
	
	@NotNull(message = "Company website can't be null")
	@NotBlank(message = "Company website can't be blank")
	private String companyWebsite;
	
	@NotNull(message = "Company size can't be null")
	@NotBlank(message = "Company size can't be blank")
	private Integer companySize;
	
	@NotNull(message = "Job title can't be null")
	@NotBlank(message = "Job title can't be blank")
	private String jobTitleRecruiter;
	


}
