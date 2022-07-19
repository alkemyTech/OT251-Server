package com.alkemy.ong.models;


import java.util.Date;
import java.util.UUID;

import javax.persistence.*;

//import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.Data;

@Data
@Entity
@Table(name= "organizations")
@SQLDelete(sql = "UPDATE organizations SET softDelete = true WHERE id=?")
@Where(clause = "softDelete=false")
public class OrganizationModel {

    @Id
    @GeneratedValue //(generator = "UUID")
    //@GenericGenerator(name = "UUID", strategy = "UUID2")
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
    private boolean softDelete = false;

    
    
    
}
/*COMO desarrollador 
QUIERO agregar la entidad Organization
PARA representar en la implementación la estructura de datos

Criterios de aceptación: 
Nombre de tabla: organizations. Los campos son:
name: VARCHAR NOT NULL
image: VARCHAR NOT NULL
address: VARCHAR NULLABLE
phone: INTEGER NULLABLE
email: VARCHAR NOT NULL
welcomeText: TEXT NOT NULL
aboutUsText: TEXT NULLABLE
timestamps y softDelete */