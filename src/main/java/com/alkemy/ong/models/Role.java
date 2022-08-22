package com.alkemy.ong.models;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "roles")
public class Role {

	@Id
	@GeneratedValue
	@Type(type = "uuid-char")
	private UUID id;

	@Column(nullable = false, length = 30)
	private String name;

	private String description;

	@CreationTimestamp
	@Column(nullable = false, updatable = false)
	private Timestamp createAt;

	@UpdateTimestamp
	@Column(nullable = false)
	private Timestamp updateAt;

	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Role(UUID id, String name, String description, Timestamp createAt, Timestamp updateAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.createAt = createAt;
		this.updateAt = updateAt;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
