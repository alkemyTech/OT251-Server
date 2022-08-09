package com.alkemy.ong.services;

import java.util.UUID;

import com.alkemy.ong.dto.request.news.NewsRequest;
import com.alkemy.ong.dto.response.news.NewsResponse;

public interface INewsServices {

	NewsResponse getById(UUID id);

	public void delete(UUID id);

	NewsResponse update(UUID id, NewsRequest newsRequest);

}
