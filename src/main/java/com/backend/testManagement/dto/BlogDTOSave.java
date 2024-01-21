package com.backend.testManagement.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTOSave {

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
    private String author;

    @NotBlank
    @Size(min = 2, max = 100)
    private String category;


}
