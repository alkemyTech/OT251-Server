package com.alkemy.ong.models;


import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name= "organizations")
public class organizacion_models {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)

     private long id;
     
     private String name;
     private String image;
     private String address;
     private int phone;
     private String email;
     @Column(columnDefinition = "TEXT")
     private String welcomeText;
     @Column(columnDefinition = "TEXT")
     private String aboutUsText;
     @Column
     @Temporal(TemporalType.TIMESTAMP)
     private Date  timestamps;
     private boolean softDelete;

     public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getWelcomeText() {
        return welcomeText;
    }
    public void setWelcomeText(String welcomeText) {
        this.welcomeText = welcomeText;
    }
    public String getAboutUsText() {
        return aboutUsText;
    }
    public void setAboutUsText(String aboutUsText) {
        this.aboutUsText = aboutUsText;
    }
    public Date getTimestamps() {
        return timestamps;
    }
    public void setTimestamps(Date timestamps) {
        this.timestamps = timestamps;
    }
    public boolean isSoftDelete() {
        return softDelete;
    }
    public void setSoftDelete(boolean softDelete) {
        this.softDelete = softDelete;
    }
    
    
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