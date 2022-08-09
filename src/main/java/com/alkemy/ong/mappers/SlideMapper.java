package com.alkemy.ong.mappers;

import org.springframework.stereotype.Component;

import com.alkemy.ong.dto.response.slides.SlideResponse;
import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.models.Slide;

@Component
public class SlideMapper {

	public SlideResponse maptoResponse(Slide slide) {
		SlideResponse slideResponse = new SlideResponse();
		slideResponse.setImageUrl(slide.getImageUrl());
		slideResponse.setOrder(slide.getOrder());
		return slideResponse;
	}

	public SlidesDetailsResponse maptoDetailsResponse(Slide slide) {
		SlidesDetailsResponse slideResponse = new SlidesDetailsResponse();
		slideResponse.setId(slide.getId());
		slideResponse.setImageUrl(slide.getImageUrl());
		slideResponse.setText(slide.getText());
		slideResponse.setOrder(slide.getOrder());
		slideResponse.setOrganizationId(slide.getOrganizationId());
		return slideResponse;
	}

}
