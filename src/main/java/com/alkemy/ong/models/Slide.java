package com.alkemy.ong.models;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false,length = 50)
    private String imageUrl;

    @Column(length = 50)
    private String text;

    @Column(length = 5)
    private String order;

    @Column(nullable = false)
    private UUID organizationId;

}
