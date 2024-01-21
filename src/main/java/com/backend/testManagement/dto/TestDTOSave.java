package com.backend.testManagement.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TestDTOSave {

        @NotBlank
        @NotNull
        @Size(min = 2, max = 100)
        private String name;

        @NotBlank
        @NotNull
        @Size(min = 2, max = 100)
        private String lastname;


}
