package com.alkemy.ong.services;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import com.alkemy.ong.dto.request.slides.SlideCreateRequest;
import com.alkemy.ong.dto.request.slides.SlideRequest;
import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.models.Organization;

public interface ISlideService {

	List<SlideResponse> getAll();

	SlidesDetailsResponse getById(UUID id);

	public SlidesDetailsResponse update(UUID id, SlideRequest slideRequest);

	void delete(UUID id);

	void create(@Valid SlideCreateRequest slideRequest);

	List<SlidesDetailsResponse> getSlidesByOrganization(Organization organization);

}
