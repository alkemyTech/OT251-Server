package com.alkemy.ong.services.impl;

import com.alkemy.ong.config.amazons3.services.impl.AWSClientServiceImpl;
import com.alkemy.ong.dto.request.testimonial.TestimonialRequest;
import com.alkemy.ong.dto.response.pagination.PageResultResponse;
import com.alkemy.ong.dto.response.testimonial.TestimonialResponse;
import com.alkemy.ong.exception.ResourceNotFoundException;
import com.alkemy.ong.mappers.PageResultMapper;
import com.alkemy.ong.mappers.TestimonialMapper;
import com.alkemy.ong.models.Testimonial;
import com.alkemy.ong.repositories.TestimonialRepository;
import com.alkemy.ong.services.ITestimonialService;
import com.alkemy.ong.utils.ClassUtil;
import com.alkemy.ong.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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
    public TestimonialResponse createTestimonial(TestimonialRequest testimonialRequest) {

        Testimonial testimonial = testimonialMapper.testimonialRequestToEntity(testimonialRequest);

        MultipartFile decodedImage = imageUtils.base64Image2MultipartFile(testimonialRequest.getImage());
        testimonial.setImage(awsService.uploadFile(decodedImage));

        return testimonialMapper.mapTestimonialResponse(testimonialRepo.save(testimonial));
    }

    @Override
    public PageResultResponse<TestimonialResponse> getAllTestimonials(Integer numberOfPage) {
        Page<Testimonial> page = getPage(numberOfPage);
        if(!page.hasContent()){
            throw new ResourceNotFoundException();
        }
        List<TestimonialResponse> testimonialResponses = testimonialMapper.entities2ListResponse(page.getContent());
        String previous = getPrevious(PATH_TESTIMONIALS, numberOfPage);
        String next = getNext(page, PATH_TESTIMONIALS, numberOfPage);

        PageResultMapper<TestimonialResponse> response = new PageResultMapper<>();
        return response.mapPage(testimonialResponses, previous, next);
    }

}