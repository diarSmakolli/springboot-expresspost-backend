package com.backend.testManagement.controller;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.*;
import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.model.Test;
import com.backend.testManagement.services.PorosiaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/porosia")
@Validated
public class PorosiaController {
    private final PorosiaService porosiaService;

    @Autowired
    public PorosiaController(PorosiaService porosiaService) {
        this.porosiaService = porosiaService;
    }



    @GetMapping(value = "/getAllPorosi")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tests")
    @ApiResponse(responseCode = "404", description = "No tests found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<CommonResponseDTO<PorosiaDTO>> getAllPorosi(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortProperty,
            @RequestParam(defaultValue = "asc") String order) {

        CommonResponseDTO<PorosiaDTO> responseDTO = porosiaService.getAllPorosi(page, size, sortProperty, order);
        return ResponseEntity.ok(responseDTO);

    }


    @GetMapping("/byUserId")
    public ResponseEntity<List<PorosiaDTO>> getPorosiaByUserId(@RequestParam String userId) {
        List<PorosiaDTO> porosiaDTOList = porosiaService.getPorosiaByUserId(userId);
        return ResponseEntity.ok(porosiaDTOList);
    }

    @GetMapping("/byUserIdAndShteti")
    public ResponseEntity<List<PorosiaDTO>> getPorosiaByUserIdAndShteti(
            @RequestParam String userId,
            @RequestParam String shteti) {
        List<PorosiaDTO> porosiaDTOList = porosiaService.getPorosiaByUserIdAndShteti(userId, shteti);
        return ResponseEntity.ok(porosiaDTOList);
    }

    @GetMapping("/numriPorosiveByUserIdAndShteti")
    public ResponseEntity<Integer> getNumriPorosiveByUserIdAndShteti(@RequestParam String userId, @RequestParam String shteti) {
        int numriPorosive = porosiaService.getNumriPorosiveByUserIdAndShteti(userId, shteti);
        return ResponseEntity.ok(numriPorosive);
    }

    

    @PostMapping(value = "/save")
    @ApiResponse(responseCode = "201", description = "Test created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<PorosiaDTO> savePorosia(@RequestBody PorosiaDTOSave porosiaDTO, @RequestParam String userId) {

        // Kontrollo validitetin e ID-së së përdoruesit
        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        PorosiaDTO savedPorosiaDTO = porosiaService.saveTest(porosiaDTO, userId);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPorosiaDTO);

    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved test by ID")
    @ApiResponse(responseCode = "404", description = "Test not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Porosia> findById(@PathVariable String id) {
        Porosia porosiaDTO = porosiaService.findTestById(id);
        return ResponseEntity.ok(porosiaDTO);
    }
    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "404", description = "Test not found")
    @ApiResponse(responseCode = "200", description = "Successfully deleted test")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Porosia> deletePorosi(@PathVariable String id) {
        Porosia porosiaDTO = porosiaService.deletePorosi(id);
        return ResponseEntity.ok(porosiaDTO);
    }

    @PostMapping("/update-status-to-derguar/{id}")
    @ApiResponse(responseCode = "200", description = "Porosia status updated to derguar successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Porosia not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<PorosiaDTO> updatePorosiaStatusToDerguar(@PathVariable String id) {
        PorosiaDTO updatedPorosiaDTO = porosiaService.updatePorosiaStatusToDerguar(id);
        return ResponseEntity.ok(updatedPorosiaDTO);
    }

    @PostMapping("/update-status-to-dorezuar/{id}")
    @ApiResponse(responseCode = "200", description = "Porosia status updated to derguar successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Porosia not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<PorosiaDTO> updatePorosiaStatusToDorezuar(@PathVariable String id) {
        PorosiaDTO updatedPorosiaDTO = porosiaService.updatePorosiaStatusToDorezuar(id);
        return ResponseEntity.ok(updatedPorosiaDTO);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Test updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Test not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<PorosiaDTO> updatePorosi(@PathVariable String id, @RequestBody PorosiaDTOSave porosiaDTO) {

        PorosiaDTO updatedPorosiaDTO = porosiaService.updatePorosi(id, porosiaDTO);
        return ResponseEntity.ok(updatedPorosiaDTO);

    }



}