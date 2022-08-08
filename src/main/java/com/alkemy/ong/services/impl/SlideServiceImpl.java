package com.alkemy.ong.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.SlideMapper;
import com.alkemy.ong.models.Slide;
import com.alkemy.ong.repositories.SlideRepository;
import com.alkemy.ong.services.ISlideService;

@Service
public class SlideServiceImpl implements ISlideService {

	@Autowired
	private SlideRepository slideRepository;

	@Autowired
	private SlideMapper slideMapper;

	@Override
	public List<SlideResponse> getAll() {
		List<Slide> slides = slideRepository.findAll();
		if (slides.isEmpty()) {
			throw new ResourceNotFoundException("List Slide");
		}
		return slides.stream().map(slide -> slideMapper.maptoResponse(slide)).collect(Collectors.toList());
	}

	@Override
	public SlidesDetailsResponse getById(UUID id) {
		Slide slide = slideRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Slide", "id", id));
		return slideMapper.maptoDetailsResponse(slide);
	}

}
