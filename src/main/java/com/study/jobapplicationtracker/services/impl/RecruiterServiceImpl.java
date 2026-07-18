package com.study.jobapplicationtracker.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.jobapplicationtracker.dtos.JobsDto;
import com.study.jobapplicationtracker.dtos.RecruiterDto;
import com.study.jobapplicationtracker.entities.Jobs;
import com.study.jobapplicationtracker.entities.Recruiter;
import com.study.jobapplicationtracker.entities.User;
import com.study.jobapplicationtracker.repositories.RecruiterRepository;
import com.study.jobapplicationtracker.repositories.UserRepository;
import com.study.jobapplicationtracker.services.RecruiterService;

@Service
public class RecruiterServiceImpl implements RecruiterService {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RecruiterRepository recruiterRepository;
	
	@Override
	public RecruiterDto addRecruiter(Integer userId, RecruiterDto recruiterDto) {
		
		User user = userRepository.findById(userId)
		.orElseThrow(()->new RuntimeException("user not found"));
		
		//need to ask what this line does
		Recruiter recruiter = modelMapper.map(recruiterDto, Recruiter.class);
		
		recruiter.setUser(user);
		
		Recruiter savedRecruiter = recruiterRepository.save(recruiter);
		
		//why not using recruiterDto variable for RecruiterDto.class
		return modelMapper.map(savedRecruiter, RecruiterDto.class);
	}

	@Override
	public List<RecruiterDto> getAllRecruiters() {
		
		List<Recruiter> recruiter = recruiterRepository.findAll();
		
		List<RecruiterDto> listOfRecruiter = recruiter.stream()
				.map((r)->modelMapper.map(r, RecruiterDto.class)).toList();
		
		return listOfRecruiter;
	}

	@Override
	public RecruiterDto getRecruiterById(Integer id) {
		
		Recruiter recruiter = recruiterRepository.findById(id)
				.orElseThrow(()->new RuntimeException("Id not found"));
				
		return modelMapper.map(recruiter, RecruiterDto.class);
		
	}

	@Override
	public RecruiterDto updateRecruiter(Integer id, RecruiterDto recruiterDto) {
		
		Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(()->new RuntimeException("id not found"));
		
		recruiter.setCompanyName(recruiterDto.getCompanyName());
		recruiter.setCompanySize(recruiterDto.getCompanySize());
		recruiter.setCompanyWebsite(recruiterDto.getCompanyWebsite());
		recruiter.setJobTitleRecruiter(recruiterDto.getJobTitleRecruiter());
		recruiter.setPhNo(recruiterDto.getPhNo());
		
		Recruiter updateRecruiter = recruiterRepository.save(recruiter);
		
		return modelMapper.map(updateRecruiter, RecruiterDto.class);
	}

	@Override
	public void deleteRecruiter(Integer id) {
		
		Recruiter recruiter = recruiterRepository.findById(id).orElseThrow(() -> new RuntimeException("Id not found"));
		recruiterRepository.delete(recruiter);
		
	}

}
