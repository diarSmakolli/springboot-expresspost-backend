package com.backend.testManagement.controller;

import com.backend.testManagement.dto.CommonResponseDTO;
import com.backend.testManagement.dto.BlogDTO;
import com.backend.testManagement.dto.BlogDTOSave;
import com.backend.testManagement.model.Blog;
import com.backend.testManagement.services.BlogService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/blog")
@Validated
public class BlogController {
    private final BlogService blogService;

    @Autowired
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping(value = "/getAll")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all blogs")
    @ApiResponse(responseCode = "404", description = "No blogs found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<CommonResponseDTO<BlogDTO>> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortProperty,
            @RequestParam(defaultValue = "asc") String order) {

        CommonResponseDTO<BlogDTO> responseDTO = blogService.getAllBlogs(page, size, sortProperty, order);
        return ResponseEntity.ok(responseDTO);

    }

    @PostMapping(value = "/create")
    @ApiResponse(responseCode = "201", description = "Test created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<BlogDTO> saveTest(@RequestBody BlogDTOSave blogDTO) {

        BlogDTO savedTestDTO = blogService.saveBlog(blogDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTestDTO);
    }

    @PutMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Blog updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "404", description = "Blog not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<BlogDTO> updateBlog(@PathVariable String id, @RequestBody BlogDTOSave blogDTO) {
        BlogDTO updatedBlogDTO = blogService.updateBlog(id, blogDTO);
        return ResponseEntity.ok(updatedBlogDTO);
    }

    @GetMapping("/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved test by ID")
    @ApiResponse(responseCode = "404", description = "Test not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    public ResponseEntity<Blog> findById(@PathVariable String id) {
        Blog blogDTO = blogService.findBlogById(id);
        return ResponseEntity.ok(blogDTO);
    }


    @DeleteMapping(value = "/{id}")
    @ApiResponse(responseCode = "404", description = "Test not found")
    @ApiResponse(responseCode = "200", description = "Successfully deleted test")
    @ApiResponse(responseCode = "400", description = "Invalid request")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Blog> deleteBlog(@PathVariable String id) {
        Blog blogDTO = blogService.deleteBlog(id);
        return ResponseEntity.ok(blogDTO);
    }


}