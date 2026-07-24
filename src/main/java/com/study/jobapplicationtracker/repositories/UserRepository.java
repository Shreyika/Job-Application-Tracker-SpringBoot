package com.study.jobapplicationtracker.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.study.jobapplicationtracker.entities.User;


public interface UserRepository extends JpaRepository<User, Integer>{

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String username);
}
