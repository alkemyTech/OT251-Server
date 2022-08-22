package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.news.NewsRequest;
import java.util.UUID;

import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import org.springframework.web.multipart.MultipartFile;


public interface INewsServices {

	public NewsResponse getById(UUID id);
        
	public NewsResponse createNews(NewsRequest newsRequest);

	public void delete(UUID id);

	public NewsResponse update(UUID id, NewsRequest newsRequest);

	public PageResultResponse<NewsResponse> getNewsList(Integer pageNumber);

}
