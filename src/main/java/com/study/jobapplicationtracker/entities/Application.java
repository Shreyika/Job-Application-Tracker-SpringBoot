package com.study.jobapplicationtracker.entities;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

import com.study.jobapplicationtracker.enums.ApplicationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Application {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Integer applicationId;
	 
	 @ManyToOne
	 private Candidate candidate;

	 @ManyToOne
	 private Jobs job;
	 
	 private String resumeUrl;
	 
	 @Enumerated(EnumType.STRING)
	 private ApplicationStatus applicationStatus;
	 
	 private LocalDate interviewDate;

	 @CreationTimestamp
	 private LocalDate appliedDate;
}
