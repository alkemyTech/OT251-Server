package com.alkemy.ong.dto.response.member;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
public class MemberResponse {

    @Schema(name = "id", description="Member ID", type="UUID",example="4581c106-d9c7-4e65-bc6b-4f113d4a0edf")
    private UUID id;
    @Schema(name = "name", description="Member name", type="String",example="name example")
    private String name;
    @Schema(name = "facebook", description="Member facebookUrl", type="String",example="facebook.com/example")
    private String facebookUrl;
    @Schema(name = "instagram", description="Member instagramUrl", type="String",example="instagram.com/example")
    private String instagramUrl;
    @Schema(name = "linkedin", description="Member linkedinUrl", type="String",example="linkedin.com/in/example")
    private String linkedinUrl;
    @Schema(name = "image", description="Member image", type="String",example="imageMember.jpg")
    private String image;
    @Schema(name = "description", description="Member description", type="String",example="Member is... ")
    private String description;
}
