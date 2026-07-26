package com.study.jobapplicationtracker.services;

import java.util.List;

import com.study.jobapplicationtracker.dtos.CandidateDto;

public interface CandidateService {

	
	CandidateDto addCandidate(Integer userId, CandidateDto candidateDto);
	
	List<CandidateDto> getAllCandidates();
	
	CandidateDto getCandidateById(Integer id);
	
	CandidateDto updateCandidate(Integer id, CandidateDto candidateDto);
	
	void deleteCandidate(Integer id);
	
	void updateResume(Integer userId, String resumeUrl);
	
	
}
