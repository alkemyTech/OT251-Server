package com.alkemy.ong.services;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.alkemy.ong.dto.request.slide.SlideRequest;
import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;

public interface ISlideService {

	List<SlideResponse>  getAll();

	SlidesDetailsResponse getById(UUID id);

	void delete(UUID id);

	void create(@Valid SlideRequest slideRequest);

}
