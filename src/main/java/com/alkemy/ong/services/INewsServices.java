package com.alkemy.ong.services;

import com.alkemy.ong.dto.request.news.NewsRequest;
import java.util.UUID;

import com.alkemy.ong.dto.response.news.NewsResponse;
import org.springframework.web.multipart.MultipartFile;

public interface INewsServices {

	NewsResponse getById(UUID id);
        
        NewsResponse createNews(NewsRequest newsRequest, MultipartFile image);

}
