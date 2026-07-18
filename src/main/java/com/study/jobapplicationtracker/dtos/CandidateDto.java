package com.study.jobapplicationtracker.dtos;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CandidateDto {

	private Integer candidateId;
	
	@NotBlank
	private String gender;
	
	@NotBlank
	private String resumeURL;
	
	@NotBlank
	private LocalDate dateOfBirth; 
	
	@NotBlank(message = "Add Fresher of no experience")
	private String currentJobTitle;
	
	@NotBlank
	private String candidateBio;
	
	@NotNull(message = "If fresher add zero")
	private Integer noticePeriod;
	
	@NotBlank
	private String candidateLocation;
	
	@NotNull(message = "If fresher add zero")
	private Integer currentCTC;
	
	@NotBlank
	private String candidatePhNo;
	
	@NotNull(message = "If fresher add zero")
	private Integer candidateExperience;
	
	
}
