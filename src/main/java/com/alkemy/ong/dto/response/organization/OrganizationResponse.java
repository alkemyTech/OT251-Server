package com.alkemy.ong.dto.response.organization;

import com.alkemy.ong.dto.response.slides.SlidesDetailsResponse;
import com.alkemy.ong.models.Slide;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class OrganizationResponse {

    private UUID id;
    private String name;
    private String image;
    private Integer phone;
    private String address;
    private String facebookUrl;
    private String linkedinUrl;
    private String instagramUrl;
    private List<SlidesDetailsResponse> slides;
}
