package com.alkemy.ong.config.amazons3.services;

import org.springframework.web.multipart.MultipartFile;

public interface IAWSClientService {

	String uploadFile(MultipartFile multipartFile);
	
	String deleteFileFromS3Bucket(String fileUrl);
}
