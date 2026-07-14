package com.study.jobapplicationtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Integer>{

}
