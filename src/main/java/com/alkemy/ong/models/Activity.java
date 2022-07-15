package com.alkemy.ong.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name = "ACTIVITIES")
@SQLDelete(sql = "UPDATE ACTIVITIES SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Activity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "UUID2")
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String content;

	@Column(nullable = false)
	private String image;
	
	@CreationTimestamp
	private Timestamp timestamps;

	@UpdateTimestamp
	private Timestamp updateAt;

	@Column(name = "soft_delete")
	private boolean softDelete = false;
}