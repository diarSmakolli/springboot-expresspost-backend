package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class PorosiaDTO {

    private String id;
    private String emriBleresit;
    private String shteti;
    private String qyteti;
    private String adresa;
    private String postcode;
    private String telefoni;
    private String emertimiPakos;
    private String pershkrimiPakos;
    private String instruksioneDorezimit;
    private Double vleraPakos;
    private String madhesia;
    private String statusi;
    private Date createdAt;
    private String userId;

}
