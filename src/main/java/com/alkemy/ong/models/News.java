package com.alkemy.ong.models;

import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import lombok.*;

@Getter
@Setter
@Entity
@SQLDelete(sql = "UPDATE news SET deleted=true WHERE id = ?")
@Where(clause = "deleted = false")
@Table(name = "news")
public class News {
    
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "UUID2")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    // private Category category;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    private Boolean deleted;

}
