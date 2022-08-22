package com.alkemy.ong.dto.request.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class MemberRequest {

    @ApiModelProperty(name = "name", position = 0, value="Member name", dataType="String",example="name example", required = true)
    @NotBlank(message = "The Name field cannot be empty.")
    private String name;

    @ApiModelProperty(name = "facebook", position = 1, value="Member facebookUrl", dataType="String",example="facebook.com/example")
    private String facebookUrl;

    @ApiModelProperty(name = "instagram", position = 2, value="Member instagramUrl", dataType="String",example="instagram.com/example")
    private String instagramUrl;

    @ApiModelProperty(name = "linkedin", position = 3, value="Member linkedinUrl", dataType="String",example="linkedin.com/in/example")
    private String linkedinUrl;

    @ApiModelProperty(name = "image", position = 4, value="Member image", dataType="String",example="imageMember.jpg", required = true)
    @NotBlank(message = "The Image field cannot be empty.")
    private String image;

    @ApiModelProperty(name = "description", position = 5, value="Member description", dataType="String",example="Member is... ")
    private String description;
}
