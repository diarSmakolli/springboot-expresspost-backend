package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class FinancatDTOSave {

//    private String statusi;
    private Double shumaKerkeses;
    private Double sherbimiPostar;
    private Double shumaTotaleNeto;

}
