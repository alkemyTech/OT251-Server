package com.alkemy.ong.config.amazons3.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.validation.constraints.AssertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import com.alkemy.ong.config.amazons3.AWSClientConfig;
import com.alkemy.ong.config.amazons3.services.IAWSClientService;
import com.alkemy.ong.models.vm.Asset;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

//import antlr.StringUtils;

@Service
public class AWSClientServiceImpl implements IAWSClientService{

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;

	@Autowired
	public AWSClientConfig awsS3ClientConfig;
	
	private AmazonS3 s3client;

	@Autowired
	private AmazonS3Client clientAS3;

	@PostConstruct
	private void initializeAmazon() {
		s3client = awsS3ClientConfig.initializeAmazon();
	}
	
	public String putObject(MultipartFile multipartFile){
		String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
		String key = String.format("%s.%s", UUID.randomUUID(), extension);

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(multipartFile.getContentType());

		try{
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, multipartFile.getInputStream(), objectMetadata)
				.withCannedAcl(CannedAccessControlList.PublicRead); 
			clientAS3.putObject(putObjectRequest);
			return key;
		}catch(IOException ex){
			throw new RuntimeException(ex);
		}
	}

	public Asset getObject(String key){
		
		S3Object s3Object = clientAS3.getObject(bucketName,key);
		ObjectMetadata metadata = s3Object.getObjectMetadata();

		try{
			S3ObjectInputStream inputStream = s3Object.getObjectContent();
			byte[] bytes = IOUtils.toByteArray(inputStream);

			return new Asset(bytes, metadata.getContentType());
		}catch(IOException ex){
			throw new RuntimeException(ex);
		}
	}

	public void deleteObject(String key){
		clientAS3.deleteObject(bucketName, key);
	}

	public String getObjectURL(String key){
		return String.format("https://%s.amazonaws.com/%s", bucketName, key);
	}

	/**
	 * Method that converts a MultipartFile to a File
	 * 
	 * @param MultipartFile A representation of an uploaded file received in a
	 *                      multipart request.
	 * @return File Represents a file or directory.
	 */
	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	/**
	 * Method that generates a unique name for each file if it is uploaded multiple
	 * times. It uses a timestamp and also replaces all spaces in the filename with
	 * underscores to avoid problems in the future.
	 * 
	 * @param MultipartFile A representation of an uploaded file received in a
	 *                      multipart request.
	 * @return String.
	 */
	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	/**
	 * Method adds PublicRead permissions to this file. It means that anyone who has
	 * the URL of the file can access this file. It's good practice for images only,
	 * because you'll likely be displaying these images on your website or mobile
	 * app, so you need to make sure every user can see them.
	 * 
	 * @param String fileName.
	 * 
	 * @param File   file
	 */
	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	/**
	 * S3 bucket can't delete file by url. It requires a bucket name and a file
	 * name, so we retrieve the file name from the url.
	 * 
	 * @param String fileUrl.
	 */
	@Override
	public String deleteFileFromS3Bucket(String fileUrl) {
		String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
		return "Successfully deleted";
	}

}
