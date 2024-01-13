package com.backend.testManagement.mapper;

import com.backend.testManagement.dto.PorosiaDTO;
import com.backend.testManagement.dto.PorosiaDTOSave;
import com.backend.testManagement.model.Porosia;
import org.springframework.stereotype.Component;

@Component
public class PorosiaMapper {

    public PorosiaDTO mapToDTO(Porosia porosia) {
        return PorosiaDTO.builder()
                .id(porosia.getId())
                .emriBleresit(porosia.getEmriBleresit())
                .shteti(porosia.getShteti())
                .qyteti(porosia.getQyteti())
                .adresa(porosia.getAdresa())
                .postcode(porosia.getPostcode())
                .telefoni(porosia.getTelefoni())
                .emertimiPakos(porosia.getEmertimiPakos())
                .pershkrimiPakos(porosia.getPershkrimiPakos())
                .instruksioneDorezimit(porosia.getInstruksioneDorezimit())
                .vleraPakos(porosia.getVleraPakos())
                .madhesia(porosia.getMadhesia())
                .statusi(porosia.getStatusi())
                .createdAt(porosia.getCreatedAt())
                .build();
    }

    public Porosia mapToEntity(PorosiaDTOSave porosiaDTO) {
        return Porosia.builder()
                .emriBleresit(porosiaDTO.getEmriBleresit())
                .shteti(porosiaDTO.getShteti())
                .qyteti(porosiaDTO.getQyteti())
                .adresa(porosiaDTO.getAdresa())
                .postcode(porosiaDTO.getPostcode())
                .telefoni(porosiaDTO.getTelefoni())
                .emertimiPakos(porosiaDTO.getEmertimiPakos())
                .pershkrimiPakos(porosiaDTO.getPershkrimiPakos())
                .instruksioneDorezimit(porosiaDTO.getInstruksioneDorezimit())
                .vleraPakos(porosiaDTO.getVleraPakos())
                .madhesia(porosiaDTO.getMadhesia())
//                .statusi(porosiaDTO.getStatusi())
                .build();
    }



}