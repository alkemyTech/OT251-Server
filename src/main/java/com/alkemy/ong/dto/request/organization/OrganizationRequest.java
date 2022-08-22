package com.alkemy.ong.dto.request.organization;

import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class OrganizationRequest {
    private UUID id;
    
    @NotBlank(message = "The name field cannot be empty.")
    @Size(min = 2, max = 30, message = "The length of the name must be greater than 2 and less than 30 characters.")
    private String name;
    
    @NotBlank(message = "The image field cannot be empty.")
    private String image;
    
    @NotEmpty(message = "The phone field cannot be empty.")
    private Integer phone;
    
    @NotBlank(message = "The address field cannot be empty.")
    private String address;

    @URL(message = "The URL must be correctly formatted.")
    private String facebookUrl;

    @URL(message = "The URL must be correctly formatted.")
    private String linkedinUrl;

    @URL(message = "The URL must be correctly formatted.")
    private String instagramUrl;
}
