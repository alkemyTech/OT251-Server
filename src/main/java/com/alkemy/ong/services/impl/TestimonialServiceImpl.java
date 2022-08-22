package com.alkemy.ong.services.impl;

import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.TestimonialMapper;
import com.alkemy.ong.models.Testimonial;
import com.alkemy.ong.repositories.TestimonialRepository;
import com.alkemy.ong.services.ITestimonialService;
import com.alkemy.ong.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

	@Autowired
	private TestimonialMapper testimonialMapper;

	@Autowired
	private TestimonialRepository testimonialRepo;

	@Autowired
	private AWSClientServiceImpl awsService;

	@Autowired
	private ImageUtils imageUtils;

	@Override
	public TestimonialResponse createTestimonial(TestimonialRequest testimoniaRequest) {

		Testimonial testimonial = testimonialMapper.testimonialRequestToEntity(testimoniaRequest);

		MultipartFile decodedImage = imageUtils.base64Image2MultipartFile(testimoniaRequest.getImage());
		testimonial.setImage(awsService.uploadFile(decodedImage));

		return testimonialMapper.mapTestimonialResponse(testimonialRepo.save(testimonial));
	}

	@Override
	public void delete(UUID id) {
		Testimonial testimonial = testimonialRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Testimonial", "id", id));
		testimonialRepo.delete(testimonial);
	}

	public TestimonialResponse update(UUID id, TestimonialRequest testimoniaRequest) {
		Testimonial testimonial = testimonialRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Testimonial", "id", id));

		testimonial.setId(testimoniaRequest.getId());
		testimonial.setName(testimoniaRequest.getName());
		testimonial.setImage(testimoniaRequest.getImage());
		testimonial.setContent(testimoniaRequest.getContent());

		return testimonialMapper.mapTestimonialResponse(testimonialRepo.save(testimonial));
	}

}
