package com.backend.testManagement.model;



import lombok.*;
import org.apache.james.mime4j.dom.datetime.DateTime;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "financat")
@Entity
@Builder
public class Financat {

    @Id
    @Column(name = "id")
    private final String id= UUID.randomUUID().toString();

    @Column(name = "statusi")
    private String statusi; // default kerkuar -> procesuar(pagesa)


    @Column(name = "shumaTotaleNeto")
    private Double shumaTotaleNeto;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }


}
