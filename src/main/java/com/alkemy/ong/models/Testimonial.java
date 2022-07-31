package com.alkemy.ong.models;

import java.sql.Timestamp;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

@Entity
@Data
@Table(name = "testimonials")
@SQLDelete(sql = "UPDATE testimonials SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class Testimonial {
    
    @Id
    @GeneratedValue
    private UUID id;
    
    @Column (nullable = false, length = 50)
    private String name;
    
    @Column (nullable = true)
    private String image;
    
    @Column (nullable = true)
    private String content;
    
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createdAt;
    
    @UpdateTimestamp
    @Column(nullable = false)
    private Timestamp updatedAt;
    
    private Boolean deleted = false;
}
