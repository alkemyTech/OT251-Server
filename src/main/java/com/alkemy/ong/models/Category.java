package com.alkemy.ong.models;

import lombok.Data;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Data
@Table(name = "categories")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Category {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    private String image;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updateAt;

    private Boolean deleted = false;

}
