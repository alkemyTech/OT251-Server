package com.alkemy.ong.config.amazons3.services;

import org.springframework.web.multipart.MultipartFile;

import com.alkemy.ong.models.vm.Asset;

public interface IAWSClientService {
	
	String deleteFileFromS3Bucket(String fileUrl);

	public String putObject(MultipartFile multipartFile);
	public Asset getObject(String key);
	public void deleteObject(String key);
	public String getObjectURL(String key);
}
