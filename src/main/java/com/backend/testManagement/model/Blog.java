package com.backend.testManagement.model;



import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blog")
@Entity
@Builder
public class Blog {

    @Id
    @Column(name = "id")
    private final String id= UUID.randomUUID().toString();

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @Column(name = "author")
    private String author;

    @Column(name = "category")
    private String category;

    @PrePersist
    protected void onCreate() {

        createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
