package com.backend.testManagement.services;

import com.backend.testManagement.dto.*;
import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.model.Test;

import java.util.List;

public interface PorosiaService {
    CommonResponseDTO<PorosiaDTO> getAllPorosi(int pageNo, int pageSize, String sortBy, String sortDirection);

    List<PorosiaDTO> getPorosiaByUserId(String userId);

    List<PorosiaDTO> getPorosiaByUserIdAndShteti(String userId, String shteti);

    int getNumriPorosiveByUserIdAndShteti(String userId, String shteti);

    int getNumriStatusitByUserId(String userId, String statusi);




    PorosiaDTO saveTest(PorosiaDTOSave porosiaDTO, String userId);
    Porosia findTestById(String id);
    Porosia deletePorosi(String id);
    PorosiaDTO updatePorosiaStatusToDerguar(String id);
    PorosiaDTO updatePorosiaStatusToDorezuar(String id);
    PorosiaDTO updatePorosi(String id, PorosiaDTOSave porosiaDTO);

}
