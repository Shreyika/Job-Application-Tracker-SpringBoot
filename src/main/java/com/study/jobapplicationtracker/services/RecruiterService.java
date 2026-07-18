package com.study.jobapplicationtracker.services;

import java.util.List;

import com.study.jobapplicationtracker.dtos.RecruiterDto;

public interface RecruiterService {

	RecruiterDto addRecruiter(Integer userId, RecruiterDto recruiterDto);
	
	List<RecruiterDto> getAllRecruiters();
	
	RecruiterDto getRecruiterById(Integer id);

	RecruiterDto updateRecruiter(Integer id, RecruiterDto recruiterDto);

	void deleteRecruiter(Integer id);
}
