package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.news.NewsRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.models.Category;
import com.alkemy.ong.models.News;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsMapper {

	public NewsResponse mapNewsResponse(News news) {
		NewsResponse newsResponse = new NewsResponse();
		newsResponse.setId(news.getId());
		newsResponse.setName(news.getName());
		newsResponse.setContent(news.getContent());
		newsResponse.setImage(news.getImage());
		newsResponse.setCategory(news.getCategory());
		return newsResponse;
	}
        
        public News newsRequestToEntity (NewsRequest newsRequest) {
				if(newsRequest == null) return null;

                News news = new News();
                news.setName(newsRequest.getName());
                news.setContent(newsRequest.getContent());
                return news;
        }

	public List<NewsResponse> entities2ListResponse(List<News> news){
		return news.stream()
				.map(this::mapNewsResponse)
				.collect(Collectors.toList());
	}
}