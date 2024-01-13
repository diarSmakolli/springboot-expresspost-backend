package com.backend.testManagement.model;



import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "porosia")
@Entity
@Builder
public class Porosia {

    @Id
    @Column(name = "id")
    private final String id= UUID.randomUUID().toString();

        @Column(name = "emriBleresit")
    private String emriBleresit;

    @Column(name = "shteti")
    private String shteti;

    @Column(name = "qyteti")
    private String qyteti;

    @Column(name = "adresa")
    private String adresa;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "telefoni")
    private String telefoni;

    @Column(name = "emertimiPakos")
    private String emertimiPakos;

    @Nullable
    @Column(name = "pershkrimiPakos")
    private String pershkrimiPakos;

    @Nullable
    @Column(name = "instruksioneDorezimit")
    private String instruksioneDorezimit;

    @Column(name = "vleraPakos")
    private Double vleraPakos;

    @Column(name = "madhesia")
    private String madhesia;

    @Column(name = "statusi")
    private String statusi;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @PrePersist
    protected void onCreate() {

        createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
