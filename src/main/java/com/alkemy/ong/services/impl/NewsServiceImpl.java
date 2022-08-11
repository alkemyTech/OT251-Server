package com.alkemy.ong.services.impl;

import com.alkemy.ong.config.amazons3.services.IAWSClientService;
import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.dto.request.news.NewsRequest;
import java.util.UUID;

import com.alkemy.ong.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.NewsMapper;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.models.News;
import com.alkemy.ong.repositories.CategoryRepository;
import com.alkemy.ong.repositories.NewsRepository;
import com.alkemy.ong.services.INewsServices;
import org.springframework.web.multipart.MultipartFile;

@Service
public class NewsServiceImpl implements INewsServices {

	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private NewsMapper newsMapper;
        
	@Autowired
	private AWSClientServiceImpl awsService;
        
	@Autowired
	private CategoryRepository categoryRepo;

	@Autowired
	private ImageUtils imageUtils;


	/*
	 * Method that searches the database for a news entity through the id.
	 *
	 * @param @Type UUID id must not be {@literal null}.
	 *
	 * @return @Type NewsResponse must not be {@literal null}.
	 *
	 * @throws ResourceNotFoundException if {@literal id} is {@literal null}.
	 *
	 */
	@Override
	public NewsResponse getById(UUID id) {
		News news = newsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("News", "id", id));
		return newsMapper.mapNewsResponse(news);
	}

        
	@Override
	public NewsResponse createNews(NewsRequest newsRequest) {
                        
		News news = newsMapper.newsRequestToEntity(newsRequest);
		news.setType("news");
            
		Category category = categoryRepo.findById(newsRequest.getCategoryId()).orElseThrow(() -> new ResourceNotFoundException("Category", "id", newsRequest.getCategoryId()));
		news.setCategory(category);

		MultipartFile decodedImage = imageUtils.base64Image2MultipartFile(newsRequest.getImageUrl());
		news.setImage(awsService.uploadFile(decodedImage));

		return newsMapper.mapNewsResponse(newsRepo.save(news));
	}


	@Override
	public void delete(UUID id) {
		News news = newsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("News", "id", id));
		news.setDeleted(true);
		newsRepo.delete(news);
	}

	@Override
	public NewsResponse update(UUID id, NewsRequest newsRequest) {
		newsRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("News", "id", id));
		News news = newsMapper.newsRequestToEntity(newsRequest);
		return newsMapper.mapNewsResponse(newsRepo.save(news));
	}

}
