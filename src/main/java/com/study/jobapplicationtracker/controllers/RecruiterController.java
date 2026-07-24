package com.study.jobapplicationtracker.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.study.jobapplicationtracker.dtos.RecruiterDto;
import com.study.jobapplicationtracker.enums.Role;
import com.study.jobapplicationtracker.repositories.JobsRepository;
import com.study.jobapplicationtracker.repositories.RecruiterRepository;
import com.study.jobapplicationtracker.services.RecruiterService;

@RestController
@RequestMapping("/recruiters")
@CrossOrigin
public class RecruiterController {

	@Autowired
	private RecruiterRepository recruiterRepository;
	
	@Autowired
	private RecruiterService recruiterService;
	
// POST - localhost:8080/recruiters/register/{userId}
	//http://localhost:8080/recruiters/register/1
	@PostMapping("/register/{userId}")
	public ResponseEntity<RecruiterDto> addRecruiter(@PathVariable Integer userId, @RequestBody RecruiterDto recruiterDto)
	{
		return new ResponseEntity<RecruiterDto>(recruiterService.addRecruiter(userId, recruiterDto), HttpStatus.CREATED);
	}
	
	//GET - localhost:8080/recruiters
	@GetMapping
	public ResponseEntity<List<RecruiterDto>> getAllRecruiters()
	{
		return ResponseEntity.ok(recruiterService.getAllRecruiters());
	}
	
	//GET - localhost:8080/recuiters/{id}
	@GetMapping("/{id}")
	public ResponseEntity<RecruiterDto> getRecruiterById(@PathVariable Integer id)
	{
		return ResponseEntity.ok(recruiterService.getRecruiterById(id));
	}
	
	//	PUT - localhost:8080/recuiters/{id}
	@PutMapping("/{id}")
	public ResponseEntity<RecruiterDto> updateRecruiter(@PathVariable Integer id, @RequestBody RecruiterDto recruiterDto) 
	{
		return ResponseEntity.ok(recruiterService.updateRecruiter(id, recruiterDto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteRecruiter(@PathVariable Integer id)
	{
		recruiterService.deleteRecruiter(id);
		//need to ask below 2 lines
		HashMap<String, String> response = new HashMap<String, String>();
		response.put("message", "Recruiter deleted");
		return ResponseEntity.ok(response);
	}
	
}
