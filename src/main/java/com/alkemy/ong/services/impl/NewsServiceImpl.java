package com.alkemy.ong.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.NewsMapper;
import com.alkemy.ong.models.News;
import com.alkemy.ong.repositories.NewsRepository;
import com.alkemy.ong.services.INewsServices;

@Service
public class NewsServiceImpl implements INewsServices {

	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private NewsMapper newsMapper;

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

}
