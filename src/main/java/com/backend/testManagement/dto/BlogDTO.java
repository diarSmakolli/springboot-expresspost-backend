package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BlogDTO {

    private String id;
    private String title;
    private String lastname;
    private String description;
    private String image;
    private LocalDateTime createdAt;

}
