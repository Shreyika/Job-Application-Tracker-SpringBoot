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
import org.springframework.web.bind.annotation.RestController;

import com.study.jobapplicationtracker.dtos.CandidateDto;
import com.study.jobapplicationtracker.repositories.CandidateRepository;
import com.study.jobapplicationtracker.services.CandidateService;

@RestController
@RequestMapping("/candidates")
@CrossOrigin
public class CandidateController {

	@Autowired
	private CandidateRepository candidateRepository;
	@Autowired
	private CandidateService candidateService;
	
	//localhost:8080/candidates/register/1
	@PostMapping("/register/{userId}")
	public ResponseEntity<CandidateDto> addCandidate(@PathVariable Integer userId, @RequestBody CandidateDto candidateDto)
	{
		return new ResponseEntity<CandidateDto>(candidateService.addCandidate(userId, candidateDto), HttpStatus.CREATED);
	}
	
	//GET - localhost:8080/candidates
		@GetMapping
		public ResponseEntity<List<CandidateDto>> getAllCandidates()
		{
			return ResponseEntity.ok(candidateService.getAllCandidates());
		}
		
		//GET - localhost:8080/candidates/{id}
		@GetMapping("/{id}")
		public ResponseEntity<CandidateDto> getCandidateById(@PathVariable Integer id)
		{
			return ResponseEntity.ok(candidateService.getCandidateById(id));
		}
		
//		PUT - localhost:8080/candidates/{id}
		@PutMapping("/{id}")
		public ResponseEntity<CandidateDto> updateCandidate(@PathVariable Integer id, @RequestBody CandidateDto candidateDto) 
		{
			return ResponseEntity.ok(candidateService.updateCandidate(id, candidateDto));
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Map<String, String>> deleteCandidate(@PathVariable Integer id)
		{
			candidateService.deleteCandidate(id);
			//need to ask below 2 lines
			HashMap<String, String> response = new HashMap<String, String>();
			response.put("message", "candidate deleted");
			return ResponseEntity.ok(response);
		}
	
}
