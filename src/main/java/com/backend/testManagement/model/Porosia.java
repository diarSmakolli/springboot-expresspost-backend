package com.backend.testManagement.model;



import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "emriBleresit")
    private String emriBleresit;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "shteti")
    private String shteti;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "qyteti")
    private String qyteti;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "adresa")
    private String adresa;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "postcode")
    private String postcode;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "telefoni")
    private String telefoni;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "emertimiPakos")
    private String emertimiPakos;

//    @Nullable
    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "pershkrimiPakos")
    private String pershkrimiPakos;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "instruksioneDorezimit")
    private String instruksioneDorezimit;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "vleraPakos")
    private Double vleraPakos;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "madhesia")
    private String madhesia;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Column(name = "statusi")
    private String statusi;

    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private Date createdAt;

    @NotNull(message = "User is required")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @PrePersist
    protected void onCreate() {

        createdAt = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }

}
