package com.study.jobapplicationtracker.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

	 
	 String uploadResume(MultipartFile file);

	    void deleteResume(String fileName);
}
