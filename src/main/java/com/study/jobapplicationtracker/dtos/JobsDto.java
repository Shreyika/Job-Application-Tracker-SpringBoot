package com.study.jobapplicationtracker.dtos;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobsDto {

	private Integer id;
	
	@NotNull
	@NotBlank
	private String jobRole;
	
	@NotNull
	@NotBlank
	private String jobDescription;
	
	@NotNull
	@NotBlank
	private String companyName;
	
	@NotNull
	@NotBlank
	
	private String jobLocation;
	
	@NotNull
	@NotBlank
	private String jobType;
	
	@NotNull
	
	private Integer ctcMin;
	
	@NotNull
	private Integer ctcMax;
	
	@NotNull
	private Integer vacancy;
	
	@NotNull
	@NotBlank
	private String jobStatus;
	
	@NotNull
	private LocalDate deadlineToApply;
	
	
	//private LocalDate createdAt; //don't show
	
	@NotNull
	@NotBlank
	private String experienceRequired;
}
