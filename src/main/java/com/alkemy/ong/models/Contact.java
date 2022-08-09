package com.alkemy.ong.models;

import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Contact {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 20)
    private String phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String message;

    private Boolean deleted = false;
}
