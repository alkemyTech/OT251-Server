package com.alkemy.ong.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Member {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false, length = 50)
	private String name;
	
	@Column(name = "facebook_url")
	private String facebookUrl;
	
	@Column(name = "instagram_url")
	private String instagramUrl;

	@Column(name = "linkedin_url")
	private String linkedinUrl;

	@Column(nullable = false)
	private String image;

	private String description;

	@CreationTimestamp
	private Timestamp timestamps;

	@UpdateTimestamp
	private Timestamp updateAt;

	private boolean deleted = false;

}
