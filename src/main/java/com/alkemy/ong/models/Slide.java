package com.alkemy.ong.models;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Data;

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

	@Column(length = 5 , name = "slide_order")
	private Integer order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Organization organizationId;

}
