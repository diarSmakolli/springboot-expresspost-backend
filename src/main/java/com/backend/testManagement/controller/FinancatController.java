package com.backend.testManagement.controller;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.FinancatDTO;
import com.backend.testManagement.dto.FinancatDTOSave;
import com.backend.testManagement.model.Financat;
import com.backend.testManagement.services.FinancatService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/financat")
@Validated
public class FinancatController {
    private final FinancatService financatService;

    @Autowired
    public FinancatController(FinancatService financatService) {
        this.financatService = financatService;
    }


    @GetMapping(value = "/llogaritBalancen")
    @ApiResponse(responseCode = "201", description = "Test created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Double> llogaritShumenEporosive(@RequestParam String userId) {

        if (userId == null || userId.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        double shumaTePorosit = financatService.llogaritShumenEPorosive(userId);
        return ResponseEntity.ok(shumaTePorosit);

    }





}