package com.alkemy.ong.mappers;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.news.NewsResponse;
import com.alkemy.ong.models.News;

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

}
