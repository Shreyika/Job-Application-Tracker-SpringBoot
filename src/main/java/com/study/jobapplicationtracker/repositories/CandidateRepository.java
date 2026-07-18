package com.study.jobapplicationtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.Candidate;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{

}
