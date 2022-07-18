package com.alkemy.ong.dto.response.contact;

import lombok.Data;

import java.util.UUID;

@Data
public class OrganizationDTO {

    private UUID id;
    private String name;
    private String image;
    private Integer phone;
    private String address;
}
