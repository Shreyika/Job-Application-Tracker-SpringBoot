package com.study.jobapplicationtracker.services.impl;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import java.util.stream.*;
//import java.util.stream.Collectors;

import com.study.jobapplicationtracker.dtos.JobsDto;
import com.study.jobapplicationtracker.entities.Jobs;
import com.study.jobapplicationtracker.repositories.JobsRepository;
import com.study.jobapplicationtracker.services.JobsService;

@Service
public class JobsServiceImpl implements JobsService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Override
	public JobsDto addJobs(JobsDto jobsDto) {
		
		Jobs jobs = modelMapper.map(jobsDto, Jobs.class);
		
		Jobs savedJobs = jobsRepository.save(jobs);
		
		return modelMapper.map(savedJobs, JobsDto.class);
	}

	@Override
	public List<JobsDto> getJobs() {
		
		List<Jobs> jobs = jobsRepository.findAll();
		
		List<JobsDto> jobsDtoList = jobs.stream()
		.map((j)->modelMapper.map(j, JobsDto.class)).toList();
		
		return jobsDtoList;
	}

	@Override
	public JobsDto getJobsById(Integer id) {
		
		Jobs jobs = jobsRepository.findById(id)
		.orElseThrow(()->new RuntimeException("Id not found"));
		
		return modelMapper.map(jobs, JobsDto.class);
	}

	@Override
	public JobsDto updateJobs(Integer id, JobsDto jobsDto) {
		
		Jobs jobs = jobsRepository.findById(id)
		.orElseThrow(()->new RuntimeException("id not found"));
		
		jobs.setCompanyName(jobsDto.getCompanyName());
		jobs.setJobDescription(jobsDto.getJobDescription());
		jobs.setJobRole(jobsDto.getJobRole());
		jobs.setJobLocation(jobsDto.getJobLocation());
		jobs.setJobStatus(jobsDto.getJobStatus());
		jobs.setCtcMin(jobsDto.getCtcMin());
		jobs.setCtcMax(jobsDto.getCtcMax());
		jobs.setDeadlineToApply(jobsDto.getDeadlineToApply());
		jobs.setExperienceRequired(jobsDto.getExperienceRequired());
		jobs.setJobType(jobsDto.getJobType());
		jobs.setVacancy(jobsDto.getVacancy());
		
		Jobs updatedJobs = jobsRepository.save(jobs);
		
		return modelMapper.map(updatedJobs, JobsDto.class);
		
		
	}

	@Override
	public void deleteJobs(Integer id) {
		
		Jobs jobs = jobsRepository.findById(id)
		.orElseThrow(()->new RuntimeException("Id not found"));
	
		jobsRepository.delete(jobs);
	}

	
	
}
