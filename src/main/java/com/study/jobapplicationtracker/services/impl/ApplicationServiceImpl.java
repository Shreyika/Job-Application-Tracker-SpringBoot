package com.study.jobapplicationtracker.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.jobapplicationtracker.dtos.ApplicationDto;
import com.study.jobapplicationtracker.entities.Application;
import com.study.jobapplicationtracker.entities.Candidate;
import com.study.jobapplicationtracker.entities.Jobs;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.enums.ApplicationStatus;
import com.study.jobapplicationtracker.repositories.ApplicationRepository;
import com.study.jobapplicationtracker.repositories.CandidateRepository;
import com.study.jobapplicationtracker.repositories.JobsRepository;
import com.study.jobapplicationtracker.repositories.UserRepository;
import com.study.jobapplicationtracker.services.ApplicationService;
import com.study.jobapplicationtracker.services.FileService;

@Service
public class ApplicationServiceImpl implements ApplicationService{
	
	@Autowired
	private ApplicationRepository applicationRepository;

	@Autowired
	private CandidateRepository candidateRepository;

	@Autowired
	private JobsRepository jobsRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private FileService fileService;

	@Override
	public ApplicationDto applyForJob(Integer jobId,
            Integer userId,
            MultipartFile resume) {

	    User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Candidate candidate = candidateRepository.findByUser(user)
	            .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    Jobs job = jobsRepository.findById(jobId)
	            .orElseThrow(() -> new RuntimeException("Job not found"));

	    

	    if(applicationRepository
	    		.existsByCandidate_CandidateIdAndJob_Id(
	    		        candidate.getCandidateId(),
	    		        job.getId()
	    		)) {
	    		    throw new RuntimeException("You have already applied for this job.");
	    		}


	    String resumeUrl;

	    if (resume != null && !resume.isEmpty()) {

	        resumeUrl = fileService.uploadResume(resume);

	    } else {

	        resumeUrl = candidate.getResumeURL();

	        if (resumeUrl == null || resumeUrl.isBlank()) {
	            throw new RuntimeException("Please upload a resume before applying.");
	        }
	    }
	    
	    Application application = new Application();

	    application.setCandidate(candidate);
	    application.setJob(job);
	    application.setResumeUrl(resumeUrl);
	    application.setApplicationStatus(ApplicationStatus.APPLIED);

	    Application saved = applicationRepository.save(application);

	    return modelMapper.map(saved, ApplicationDto.class);
	}

	@Override
	public List<ApplicationDto> getCandidateApplications(Integer userId) {
		
		User user = userRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    Candidate candidate = user.getCandidate();

	    List<Application> applications =
	            applicationRepository.findByCandidate(candidate);

	    return applications.stream()
	            .map(application -> modelMapper.map(application, ApplicationDto.class))
	            .toList();
	}

	@Override
	public List<ApplicationDto> getApplicationsByJob(Integer jobId) {
		 
		Jobs job = jobsRepository.findById(jobId)
		            .orElseThrow(() -> new RuntimeException("Job not found"));

		    List<Application> applications =
		            applicationRepository.findByJob(job);

		    return applications.stream()
		            .map(application -> modelMapper.map(application, ApplicationDto.class))
		            .toList();
	}

	@Override
	public ApplicationDto updateStatus(Integer applicationId, ApplicationStatus status) 
	{
		
		Application application = applicationRepository.findById(applicationId)
	            .orElseThrow(() -> new RuntimeException("Application not found"));

	    application.setApplicationStatus(status);

	    Application updatedApplication = applicationRepository.save(application);

	    return modelMapper.map(updatedApplication, ApplicationDto.class);
	}

	@Override
	public void withdrawApplication(Integer applicationId) {
		
		Application application = applicationRepository.findById(applicationId)
	            .orElseThrow(() -> new RuntimeException("Application not found"));

	    applicationRepository.delete(application);
		
	}

}
