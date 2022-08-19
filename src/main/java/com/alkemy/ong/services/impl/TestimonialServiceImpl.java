package com.alkemy.ong.services.impl;

import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.mappers.TestimonialMapper;
import com.alkemy.ong.models.Testimonial;
import com.alkemy.ong.dto.response.testimonial.TestimonialPageResponse;
import com.alkemy.ong.repositories.TestimonialRepository;
import com.alkemy.ong.services.ITestimonialService;
import com.alkemy.ong.utils.ClassUtil;
import com.alkemy.ong.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static com.alkemy.ong.utils.ApiConstants.PATH_TESTIMONIALS;

@Service
public class TestimonialServiceImpl  extends ClassUtil<Testimonial, UUID, TestimonialRepository> implements ITestimonialService {

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
    public TestimonialPageResponse getAllTestimonials(Integer numberOfPage) {
        Page<Testimonial> page = getPage(numberOfPage);

        String previous = getPrevious(PATH_TESTIMONIALS, numberOfPage);

        String next = getNext(page, PATH_TESTIMONIALS, numberOfPage);

        return testimonialMapper.entityPage2PageResponse(page.getContent(), previous, next);
    }

}