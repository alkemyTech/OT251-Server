package com.alkemy.ong.dto.response.activity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class ActivityResponse {

    private UUID id;
    private String name;
    private String content;
    private String image;
}
