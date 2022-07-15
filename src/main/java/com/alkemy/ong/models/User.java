package com.alkemy.ong.models;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "UUID2")
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String photo;

    @ManyToMany
    @JoinColumn(name = "roleId")
    private List<Role> role;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updateAt;

    private Boolean deleted = false;

}
