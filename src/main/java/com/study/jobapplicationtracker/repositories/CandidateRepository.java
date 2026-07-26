package com.study.jobapplicationtracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.Candidate;
import com.study.jobapplicationtracker.entities.User;

public interface CandidateRepository extends JpaRepository<Candidate, Integer>{
	Optional<Candidate> findByUser(User user);
}
