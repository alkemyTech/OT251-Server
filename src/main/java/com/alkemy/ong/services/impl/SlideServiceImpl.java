package com.alkemy.ong.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alkemy.ong.config.amazons3.services.IAWSClientService;
import com.alkemy.ong.dto.request.slide.SlideRequest;
import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.SlideMapper;
import com.alkemy.ong.models.Slide;
import com.alkemy.ong.repositories.SlideRepository;
import com.alkemy.ong.services.ISlideService;
import com.alkemy.ong.utils.ImageUtils;

@Service
public class SlideServiceImpl implements ISlideService {

	@Autowired
	private SlideRepository slideRepository;

	@Autowired
	private SlideMapper slideMapper;

	@Autowired
	private IAWSClientService awsClientService;

	@Autowired
	private ImageUtils imageUtils;

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
		Slide slide = slideRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Slide", "id", id));
		return slideMapper.maptoDetailsResponse(slide);
	}

	@Override
	public void create(SlideRequest slideRequest) throws IllegalArgumentException {
		Slide slide = slideMapper.map2Entity(slideRequest);
		MultipartFile decodedImage = imageUtils.base64Image2MultipartFile(slideRequest.getImageUrl());
		slide.setImageUrl(awsClientService.uploadFile(decodedImage));
		slide.setOrder(getOrderOrDefault(slideRequest.getOrder()));
		slideRepository.save(slide);
	}

	private int getOrderOrDefault(Integer order) {
		if (!slideRepository.existsByOrder(order)) {
			return (order == null) ? slideRepository.getMaxOrder() + 1 : order;
		}
		throw new IllegalArgumentException("Order already exists on the slide ");
	}
}
