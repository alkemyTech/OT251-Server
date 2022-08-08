package com.alkemy.ong.services;

import java.util.List;
import java.util.UUID;

import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;

public interface ISlideService {

	List<SlideResponse>  getAll();

	SlidesDetailsResponse getById(UUID id);

}