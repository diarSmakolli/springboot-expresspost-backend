package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
public class PorosiaDTO {

    private String id;

    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String emriBleresit;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String shteti;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String qyteti;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String adresa;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String postcode;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String telefoni;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String emertimiPakos;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String pershkrimiPakos;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String instruksioneDorezimit;
    @NotBlank(message = "This field is required!")
    @NotNull
    private Double vleraPakos;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String madhesia;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String statusi;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private Date createdAt;
    @NotBlank(message = "This field is required!")
    @NotNull
    @Size(min = 2, max = 100)
    private String userId;

}
