package com.study.jobapplicationtracker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);
}
