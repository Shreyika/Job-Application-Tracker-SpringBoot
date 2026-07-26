package com.study.jobapplicationtracker.entities;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Jobs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	//private User recruiter;
	@Column(nullable = false,length = 60)
	private String jobRole;
	
	@Column(nullable = false)
	private String jobDescription;
	
	@Column(nullable = false,length = 60)
	private String companyName;
	
	@Column(nullable = false,length = 60)
	private String jobLocation;
	
	@Column(nullable = false,length = 60)
	private String jobType;
	
	@Column(nullable = false)
	private Integer ctcMin;
	
	@Column(nullable = false)
	private Integer ctcMax;
	
	@Column(nullable = false)
	private Integer vacancy;
	
	@Column(nullable = false)
	private String jobStatus;
	
	@Column(nullable = false)
	private LocalDate deadlineToApply;
	
	//this should automatically take creation date
	@CreationTimestamp
	private LocalDate createdAt; //don't show
	
	@Column(nullable = false)
	private String experienceRequired;
	
	@OneToMany(mappedBy = "job")
	@JsonIgnore
	private List<Application> applications;
	
	
	
}
