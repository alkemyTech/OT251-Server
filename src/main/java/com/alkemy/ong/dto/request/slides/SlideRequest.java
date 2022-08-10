package com.alkemy.ong.dto.request.slides;

import com.alkemy.ong.models.Organization;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class SlideRequest {

    private UUID id;

    private String imageUrl;

    private String text;

    @NotBlank(message = "The order field cannot be empty.")
    private String order;

    private Organization organizationId;

}
