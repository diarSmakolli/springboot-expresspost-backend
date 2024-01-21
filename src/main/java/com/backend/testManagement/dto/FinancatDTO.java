package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Builder
public class FinancatDTO {

    private String id;
    private String userId;
    private String porosiaId;
    private String statusi;
    private Double shumaKerkeses;
    private Double sherbimiPostar;
    private Double shumaTotaleNeto;
    private Date createdAt;


}
