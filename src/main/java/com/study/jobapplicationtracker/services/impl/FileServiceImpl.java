package com.study.jobapplicationtracker.services.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.study.jobapplicationtracker.services.FileService;
@Service
public class FileServiceImpl implements FileService{

	 @Value("${file.upload-dir}")
	 private String uploadDir;
	
	@Override
	public String uploadResume(MultipartFile file) {
		try {

            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalFileName = file.getOriginalFilename();

            String fileName =
                    UUID.randomUUID() + "_" + originalFileName;

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("Unable to upload file.");
        }
	}

	@Override
	public void deleteResume(String fileName) {
		 try {

	            Path filePath = Paths.get(uploadDir).resolve(fileName);

	            Files.deleteIfExists(filePath);

	        } catch (IOException e) {
	            throw new RuntimeException("Unable to delete file.");
	        }
		
	}

}
