package com.study.jobapplicationtracker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.Application;
import com.study.jobapplicationtracker.entities.Candidate;
import com.study.jobapplicationtracker.entities.Jobs;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

	boolean existsByCandidateAndJob(Candidate candidate, Jobs job);

	List<Application> findByCandidate(Candidate candidate);

	List<Application> findByJob(Jobs job);

}
