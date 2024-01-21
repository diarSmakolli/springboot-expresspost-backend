package com.backend.testManagement.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
public class TestDTO {

    private String id;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    @NotBlank
    @NotNull
    @Size(min = 2, max = 100)
    private String lastname;


}
