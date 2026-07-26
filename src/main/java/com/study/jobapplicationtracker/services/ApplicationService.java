package com.study.jobapplicationtracker.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.study.jobapplicationtracker.dtos.ApplicationDto;
import com.study.jobapplicationtracker.enums.ApplicationStatus;

public interface ApplicationService {

	ApplicationDto applyForJob(Integer jobId,
            Integer userId,
            MultipartFile resume);
	
	List<ApplicationDto> getCandidateApplications(Integer userId);

	List<ApplicationDto> getApplicationsByJob(Integer jobId);

	ApplicationDto updateStatus(Integer applicationId, ApplicationStatus status);

	void withdrawApplication(Integer applicationId);
}
