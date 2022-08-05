package com.alkemy.ong.services;

import java.util.UUID;

import com.alkemy.ong.dto.response.news.NewsResponse;

public interface INewsServices {

	NewsResponse getById(UUID id);

}
