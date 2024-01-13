package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class BlogDTOSave {

    private String title;
    private String lastname;
    private String description;
    private String image;

}
