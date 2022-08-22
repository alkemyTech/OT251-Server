package com.alkemy.ong.services.impl;

import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.dto.request.news.NewsRequest;

import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.mappers.PageResultMapper;
import com.alkemy.ong.utils.ClassUtils;
import com.alkemy.ong.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import static com.alkemy.ong.utils.ApiConstants.PATH_NEWS;

@Service
public class NewsServiceImpl extends ClassUtils<News, UUID, NewsRepository> implements INewsServices {

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

	@Override
	public PageResultResponse<NewsResponse> getNewsList(Integer pageNumber) {
		Page<News> page = getPage(pageNumber);
		if(!page.hasContent()){
			throw new ResourceNotFoundException();
		}
		List<NewsResponse> newsResponses = newsMapper.entities2ListResponse(page.getContent());
		String previous = getPrevious(PATH_NEWS, pageNumber);
		String next = getNext(page, PATH_NEWS, pageNumber);

		PageResultMapper<NewsResponse> response = new PageResultMapper<>();
		return response.mapPage(newsResponses, previous, next);
	}
}
