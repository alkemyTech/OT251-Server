package com.alkemy.ong.dto.request.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberRequest {

    @Schema(name = "name", description="Member name", type="String", example="name example", required = true)
    @NotBlank(message = "The Name field cannot be empty.")
    private String name;

    @Schema(name = "facebook", description="Member facebookUrl", type="String",example="facebook.com/example")
    private String facebookUrl;

    @Schema(name = "instagram", description="Member instagramUrl", type="String",example="instagram.com/example")
    private String instagramUrl;

    @Schema(name = "linkedin", description="Member linkedinUrl", type="String",example="linkedin.com/in/example")
    private String linkedinUrl;

    @Schema(name = "image", description="Member image", type="String",example="imageMember.jpg", required = true)
    @NotBlank(message = "The Image field cannot be empty.")
    private String image;

    @Schema(name = "description", description="Member description", type="String",example="Member is... ")
    private String description;
}
