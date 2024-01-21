package com.backend.testManagement.mapper;

import com.backend.testManagement.dto.FinancatDTO;
//import com.backend.testManagement.dto.PorosiaDTOSave;
import com.backend.testManagement.dto.FinancatDTOSave;
import com.backend.testManagement.model.Financat;
import org.springframework.stereotype.Component;

@Component
public class FinancatMapper {

    public FinancatDTO mapToDTO(Financat financat) {
        return FinancatDTO.builder()
                .id(financat.getId())
                .statusi(financat.getStatusi())
                .shumaKerkeses(financat.getShumaKerkeses())
                .sherbimiPostar(financat.getSherbimiPostar())
                .shumaTotaleNeto(financat.getShumaTotaleNeto())
                .createdAt(financat.getCreatedAt())
                .build();
    }

    public Financat mapToEntity(FinancatDTOSave financatDTO) {
        return Financat.builder()
                .shumaKerkeses(financatDTO.getShumaKerkeses())
                .sherbimiPostar(financatDTO.getSherbimiPostar())
                .shumaTotaleNeto(financatDTO.getShumaTotaleNeto())
                .build();
    }



}