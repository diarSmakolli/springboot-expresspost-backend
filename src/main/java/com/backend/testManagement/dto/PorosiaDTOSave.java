package com.backend.testManagement.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PorosiaDTOSave {

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
//    private String userId;
}
