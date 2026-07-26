package com.study.jobapplicationtracker.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.jobapplicationtracker.dtos.CandidateDto;

import com.study.jobapplicationtracker.entities.Candidate;

import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.enums.Role;
import com.study.jobapplicationtracker.repositories.CandidateRepository;
import com.study.jobapplicationtracker.repositories.UserRepository;
import com.study.jobapplicationtracker.services.CandidateService;

@Service
public class CandidateServiceImpl implements CandidateService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CandidateRepository candidateRepository ;
	
	@Override
	public CandidateDto addCandidate(Integer userId, CandidateDto candidateDto) {
		
		User user = userRepository.findById(userId)
		.orElseThrow(()->new RuntimeException("user not found"));
		
		//user.setRole(Role.ROLE_CANDIDATE);
		
		//User savedUser = userRepository.save(user);
		
		//need to ask what this line does
		Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
		
		candidate.setUser(user);
		
		Candidate saveCandidate = candidateRepository.save(candidate);
		
		//why not using CandidateDto variable for CandidateDto.class
		return modelMapper.map(saveCandidate, CandidateDto.class);
	}

	@Override
	public List<CandidateDto> getAllCandidates() {
		
		List<Candidate> candidate = candidateRepository.findAll();
		
		List<CandidateDto> listOfCandidate = candidate.stream()
				.map((r)->modelMapper.map(r, CandidateDto.class)).toList();
		
		return listOfCandidate;
	}

	@Override
	public void deleteCandidate(Integer id) {
		
		Candidate candidate = candidateRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
		candidateRepository.delete(candidate);
		
	}

	@Override
	public CandidateDto getCandidateById(Integer id) {
		Candidate candidate = candidateRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not found"));
				
		return modelMapper.map(candidate, CandidateDto.class);
		
	}

	@Override
	public CandidateDto updateCandidate(Integer id, CandidateDto candidateDto) {
		
		Candidate candidate = candidateRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
		
		candidate.setCandidateBio(candidateDto.getCandidateBio());
		candidate.setCandidateExperience(candidateDto.getCandidateExperience());
		candidate.setCandidateLocation(candidateDto.getCandidateLocation());
		candidate.setCandidatePhNo(candidateDto.getCandidatePhNo());
		candidate.setCurrentCTC(candidateDto.getCurrentCTC());
		candidate.setCurrentJobTitle(candidateDto.getCurrentJobTitle());
		candidate.setDateOfBirth(candidateDto.getDateOfBirth());
		candidate.setGender(candidateDto.getGender());
		candidate.setNoticePeriod(candidateDto.getNoticePeriod());
		candidate.setResumeURL(candidateDto.getResumeURL());
		
		
		Candidate updatedCandidate = candidateRepository.save(candidate);
		
		return modelMapper.map(updatedCandidate, CandidateDto.class);
		
	}
	
	@Override
	public void updateResume(Integer userId, String resumeUrl) {

	    Candidate candidate = candidateRepository.findById(userId)
	            .orElseThrow(() -> new RuntimeException("Candidate not found"));

	    candidate.setResumeURL(resumeUrl);

	    candidateRepository.save(candidate);
	}
}
