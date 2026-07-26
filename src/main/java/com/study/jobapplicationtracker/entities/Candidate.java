package com.study.jobapplicationtracker.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer candidateId;
	
	@Column(nullable = false)
	private String gender;
	
	@Column(nullable = false)
	private String resumeURL;
	
	@Column(nullable = false)
	private LocalDate dateOfBirth; 
	
	@Column(nullable = false)
	private String currentJobTitle;
	
	@Column(nullable = false)
	private String candidateBio;
	
	@Column(nullable = false)
	private Integer noticePeriod;
	
	@Column(nullable = false)
	private String candidateLocation;
	
	@Column(nullable = false)
	private Integer currentCTC;
	
	@Column(nullable = false)
	private String candidatePhNo;
	
	@Column(nullable = false)
	private Integer candidateExperience;
	
	@OneToOne
	@JsonIgnore
	private User user;
	
	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<Application> applications;
	
	
}
