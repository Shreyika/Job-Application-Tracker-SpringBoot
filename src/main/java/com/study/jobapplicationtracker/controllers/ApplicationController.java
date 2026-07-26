package com.study.jobapplicationtracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.study.jobapplicationtracker.dtos.ApplicationDto;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.enums.ApplicationStatus;
import com.study.jobapplicationtracker.repositories.ApplicationRepository;
import com.study.jobapplicationtracker.services.ApplicationService;
import com.study.jobapplicationtracker.services.FileService;

@RestController
@RequestMapping("/applications")
@CrossOrigin
public class ApplicationController {

	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private FileService fileService;
	
	@PostMapping("/{jobId}")
	public ResponseEntity<ApplicationDto> applyForJob(
	        @PathVariable Integer jobId,
	        @RequestParam(value = "resume", required = false) MultipartFile resume,
	        @AuthenticationPrincipal User user) {

	    ApplicationDto dto =
	            applicationService.applyForJob(jobId, user.getId(), resume);

	    return new ResponseEntity<>(dto, HttpStatus.CREATED);
	}
	
	// Candidate - View my applications - getCandidateApplications in service
	@GetMapping("/my")
	public ResponseEntity<List<ApplicationDto>> getMyApplications(
	        @AuthenticationPrincipal User user) {

	    return ResponseEntity.ok(
	            applicationService.getCandidateApplications(user.getId()));
	}
	
	// Recruiter - View applications for a job
	@GetMapping("/job/{jobId}")
	public ResponseEntity<List<ApplicationDto>> getApplicationsByJob(
	        @PathVariable Integer jobId) {

	    return ResponseEntity.ok(
	            applicationService.getApplicationsByJob(jobId));
	}
	
	// Recruiter - Update application status
	@PutMapping("/{applicationId}")
	public ResponseEntity<ApplicationDto> updateStatus(
	        @PathVariable Integer applicationId,
	        @RequestParam ApplicationStatus status) {

	    return ResponseEntity.ok(
	            applicationService.updateStatus(applicationId, status));
	}

	// Candidate - Withdraw application
	@DeleteMapping("/{applicationId}")
	public ResponseEntity<String> withdrawApplication(
	        @PathVariable Integer applicationId) {

	    applicationService.withdrawApplication(applicationId);
	    return ResponseEntity.ok("Application withdrawn successfully.");
	}
}
