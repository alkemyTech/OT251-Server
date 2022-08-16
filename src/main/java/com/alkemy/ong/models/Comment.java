package com.alkemy.ong.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment {

	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	private UUID id;

	@NotNull
	private String body;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "user_id")
	private User userId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "news_id")
	private News newsId;

	@CreationTimestamp
	private Timestamp timestamps;

	@UpdateTimestamp
	private Timestamp updateAt;
}
