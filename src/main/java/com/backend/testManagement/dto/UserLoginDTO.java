package com.backend.testManagement.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserLoginDTO {
    @NotBlank(message = "")
    @NotEmpty(message = "")
    @NotNull(message = "")
    @Size(min = 2, max = 100)
    private String Username;
    @JsonProperty("Fjalkalimi")
    @NotBlank(message = "Fjalkalimi eshte i detyrueshem")
    @NotEmpty(message = "Fjalkalimi eshte i detyrueshem")
    @NotNull(message = "Fjalkalimi eshte i detyrueshem")
    @Size(min = 8)
    private String Password;
}
