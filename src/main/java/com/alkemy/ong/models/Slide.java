package com.alkemy.ong.models;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "slides")
public class Slide {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    private String imageUrl;

    private String text;

    @Column(length = 5)
    private String order;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Organization organizationId;

}
