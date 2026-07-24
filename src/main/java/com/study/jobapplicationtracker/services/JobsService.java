package com.study.jobapplicationtracker.services;

import java.util.List;

import com.study.jobapplicationtracker.dtos.JobsDto;

public interface JobsService {

	JobsDto addJobs(JobsDto jobsDto,int recruiterId);
	
	List<JobsDto> getJobs();
	
	JobsDto getJobsById(Integer id);
	
	JobsDto updateJobs(Integer id, JobsDto jobsDto);
	
	void deleteJobs(Integer id);
}
