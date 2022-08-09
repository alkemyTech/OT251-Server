package com.alkemy.ong.mappers;

import com.alkemy.ong.dto.request.category.CategoryRequest;
import com.alkemy.ong.dto.request.news.NewsRequest;
import com.alkemy.ong.dto.response.category.CategoryResponse;
import com.alkemy.ong.dto.response.category.CategorySlimResponse;
import com.alkemy.ong.models.Category;
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

	public News newsRequestToNews(NewsRequest newsRequest){

		if(newsRequest==null)
			return null;

		News news = new News();

		news.setId(newsRequest.getId());
		news.setName(newsRequest.getName());
		news.setContent(newsRequest.getContent());
		news.setImage(newsRequest.getImage());
		news.setCategory(newsRequest.getCategory());

		return news;
	}

}
