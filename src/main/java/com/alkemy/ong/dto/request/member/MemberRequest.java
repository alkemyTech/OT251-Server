package com.alkemy.ong.dto.request.member;

import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class MemberRequest {

    @NotBlank(message = "The Name field cannot be empty.")
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    @NotBlank(message = "The Image field cannot be empty.")
    private String image;
    private String description;
}
