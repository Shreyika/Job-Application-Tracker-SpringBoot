package com.study.jobapplicationtracker.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Recruiter {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recruiterId;
	
	@Column(nullable = false)
	private String companyName;
	
	@Column(nullable = false)
	private String phNo; 
	
	@Column(nullable = false)
	private String companyWebsite;
	
	@Column(nullable = false)
	private Integer companySize;
	
	@Column(nullable = false)
	private String jobTitleRecruiter;
	
	@OneToOne
	private User user;
}
