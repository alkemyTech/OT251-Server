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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "UUID2")
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updateAt;

}
