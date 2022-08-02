package com.alkemy.ong.dto.response.organization;

import lombok.Data;

import java.util.UUID;

@Data
public class OrganizationResponse {

    private UUID id;
    private String name;
    private String image;
    private Integer phone;
    private String address;
}
