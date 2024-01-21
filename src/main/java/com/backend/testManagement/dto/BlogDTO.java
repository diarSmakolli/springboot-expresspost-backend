package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
public class BlogDTO {

    private String id;

    @NotBlank
    @Size(min = 2, max = 100)
    private String title;

    @NotBlank
    @Size(min = 2, max = 100)
    private String description;

    @NotBlank
    @Size(min = 2, max = 100)
    private String image;

    @NotBlank
    @Size(min = 2, max = 100)
    private Date createdAt;

    @NotBlank
    @Size(min = 2, max = 100)
    private String author;

    @NotBlank
    @Size(min = 2, max = 100)
    private String category;


}
