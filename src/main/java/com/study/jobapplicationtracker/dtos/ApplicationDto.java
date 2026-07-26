package com.study.jobapplicationtracker.dtos;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.study.jobapplicationtracker.enums.ApplicationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDto {

	private Integer applicationId;
	
	// Candidate Details
    private Integer candidateId;
    private String candidateName;

    // Job Details
    private Integer jobId;
    private String jobRole;
    
    private String companyName;
	 
	 private String resumeUrl;
	 
	 private ApplicationStatus applicationStatus;
	 
	 private LocalDate interviewDate;
	 
	 private LocalDate appliedDate;
}
