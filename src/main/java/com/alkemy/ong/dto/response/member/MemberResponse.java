package com.alkemy.ong.dto.response.member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class MemberResponse {

    @ApiModelProperty(name = "id", position = 0, value="Member ID", dataType="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf")
    private UUID id;
    @ApiModelProperty(name = "name", position = 1, value="Member name", dataType="String",example="name example")
    private String name;
    @ApiModelProperty(name = "facebook", position = 2, value="Member facebookUrl", dataType="String",example="facebook.com/example")
    private String facebookUrl;
    @ApiModelProperty(name = "instagram", position = 3, value="Member instagramUrl", dataType="String",example="instagram.com/example")
    private String instagramUrl;
    @ApiModelProperty(name = "linkedin", position = 4, value="Member linkedinUrl", dataType="String",example="linkedin.com/in/example")
    private String linkedinUrl;
    @ApiModelProperty(name = "image", position = 5, value="Member image", dataType="String",example="imageMember.jpg")
    private String image;
    @ApiModelProperty(name = "description", position = 6, value="Member description", dataType="String",example="Member is... ")
    private String description;
}
