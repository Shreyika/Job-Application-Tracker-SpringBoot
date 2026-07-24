package com.study.jobapplicationtracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.jobapplicationtracker.dtos.JobsDto;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.repositories.JobsRepository;
import com.study.jobapplicationtracker.services.JobsService;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/jobs")
@CrossOrigin
public class JobsController {
	
	@Autowired
	private  JobsRepository jobsRepository;
	
	@Autowired
	private JobsService jobsService;
	
//	JobsController(JobsRepository jobsRepository)
//	{
//		this.jobsRepository=jobsRepository;
//	}
	
//	-----------------------------------------------------
//	 POST - localhost:8080/products
//
	@PostMapping
	public ResponseEntity<JobsDto> addJobs(@Valid @RequestBody JobsDto jobs,@AuthenticationPrincipal User user) //@AuthenticationPrincipal User user
	{
		//		return new ResponseEntity<JobsDto>(jobsService.addJobs(jobs,user.getRecruiter().getRecruiterId()),HttpStatus.CREATED);

		return new ResponseEntity<JobsDto>(jobsService.addJobs(jobs, user.getId()),HttpStatus.CREATED);
	}
	
//	GET - localhost:8080/jobs	
	
	@GetMapping
	public ResponseEntity<List<JobsDto>> getJobs()
	{
		return ResponseEntity.ok(jobsService.getJobs());
	}
	
//	GET - localhost:8080/jobs/{id}
//	-----------------------------------------------------------------------
	@GetMapping("/{id}")	
	public ResponseEntity<JobsDto> getJobsById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(jobsService.getJobsById(id));
	}
	
//	DELETE - localhost:8080/jobs/{id}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteJobs(@PathVariable Integer id)
	{
		jobsService.deleteJobs(id);
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("messae", "Job deletion successfull");
		return ResponseEntity.ok(response);
	}
	
//	PUT - localhost:8080/products/{id}
	@PutMapping("/{id}")
	public ResponseEntity<JobsDto> updateJobs(@PathVariable Integer id, @RequestBody JobsDto jobsDto)
	{
		return ResponseEntity.ok(jobsService.updateJobs(id, jobsDto));
	}
	
}
