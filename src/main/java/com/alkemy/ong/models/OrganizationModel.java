package com.alkemy.ong.models;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name= "organizations")
@SQLDelete(sql = "UPDATE organizations SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class OrganizationModel {

    @Id
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String image;
    private String address;
    private int phone;
    @Column(nullable = false)
    private String email;
    @Column(columnDefinition = "TEXT",nullable = false)
    private String welcomeText;
    @Column(columnDefinition = "TEXT")
    private String aboutUsText;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamps;
    @Column(nullable = false)
    private boolean deleted = false;

    
    
    
}