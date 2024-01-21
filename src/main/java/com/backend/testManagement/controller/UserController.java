package com.backend.testManagement.controller;

import com.backend.testManagement.dto.*;
import com.backend.testManagement.model.Test;
import com.backend.testManagement.model.User;
import com.backend.testManagement.services.TestService;
import com.backend.testManagement.services.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Validated
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping(value = "/getAll")
//    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tests")
//    @ApiResponse(responseCode = "404", description = "No tests found")
//    @ApiResponse(responseCode = "500", description = "Internal server error")
//    @ApiResponse(responseCode = "400", description = "Invalid request")
//    @ApiResponse(responseCode = "403", description = "Forbidden")
//    @ApiResponse(responseCode = "401", description = "Unauthorized")
//    public ResponseEntity<CommonResponseDTO<TestDTO>> getAllTests(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size,
//            @RequestParam(defaultValue = "id") String sortProperty,
//            @RequestParam(defaultValue = "asc") String order) {
//
//        CommonResponseDTO<TestDTO> responseDTO = testService.getAllTests(page, size, sortProperty, order);
//        return ResponseEntity.ok(responseDTO);
//
//    }


    @PostMapping("/createUser")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tests")
    @ApiResponse(responseCode = "404", description = "No tests found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserSaveDTO userSaveDTO){
        userService.createUser(userSaveDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }

    @PostMapping("/login")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tests")
    @ApiResponse(responseCode = "404", description = "No tests found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Invalid request")
//    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
//        String tokenResponse = userService.loginUser(userLoginDTO);
//        return new ResponseEntity<>(tokenResponse, HttpStatus.OK);
//    }
    public ResponseEntity<String> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        String tokenResponse = userService.loginUser(userLoginDTO);
        return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
    }

    @GetMapping("/getAllUsers")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all tests")
    @ApiResponse(responseCode = "404", description = "No tests found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }





//    @PostMapping(value = "/save")
//    @ApiResponse(responseCode = "201", description = "Test created successfully")
//    @ApiResponse(responseCode = "400", description = "Invalid request")
//    @ApiResponse(responseCode = "500", description = "Internal server error")
//    @ApiResponse(responseCode = "403", description = "Forbidden")
//    @ApiResponse(responseCode = "401", description = "Unauthorized")
//    public ResponseEntity<TestDTO> saveTest(@RequestBody TestDTOSave testDTO) {
//
//        TestDTO savedTestDTO = testService.saveTest(testDTO);
//        return ResponseEntity
//                .status(HttpStatus.CREATED)
//                .body(savedTestDTO);
//
//    }


//    @PutMapping("/{id}")
//    @ApiResponse(responseCode = "200", description = "Test updated successfully")
//    @ApiResponse(responseCode = "400", description = "Invalid request")
//    @ApiResponse(responseCode = "404", description = "Test not found")
//    @ApiResponse(responseCode = "500", description = "Internal server error")
//    @ApiResponse(responseCode = "403", description = "Forbidden")
//    @ApiResponse(responseCode = "401", description = "Unauthorized")
//    public ResponseEntity<TestDTO> updateTest(@PathVariable String id, @RequestBody TestDTOSave testDTO) {
//
//        TestDTO updatedTestDTO = testService.updateTest(id, testDTO);
//        return ResponseEntity.ok(updatedTestDTO);
//
//    }

//    @GetMapping("/{id}")
//    @ApiResponse(responseCode = "200", description = "Successfully retrieved test by ID")
//    @ApiResponse(responseCode = "404", description = "Test not found")
//    @ApiResponse(responseCode = "500", description = "Internal server error")
//    public ResponseEntity<Test> findById(@PathVariable String id) {
//        Test testDTO = testService.findTestById(id);
//        return ResponseEntity.ok(testDTO);
//    }
//
//    @DeleteMapping(value = "/{id}")
//    @ApiResponse(responseCode = "404", description = "Test not found")
//    @ApiResponse(responseCode = "200", description = "Successfully deleted test")
//    @ApiResponse(responseCode = "400", description = "Invalid request")
//    @ApiResponse(responseCode = "403", description = "Forbidden")
//    @ApiResponse(responseCode = "401", description = "Unauthorized")
//    public ResponseEntity<Test> deteleteTest(@PathVariable String id) {
//        Test testDTO = testService.deleteTest(id);
//        return ResponseEntity.ok(testDTO);
//    }

}