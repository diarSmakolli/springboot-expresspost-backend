package com.backend.testManagement.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class UserSaveDTO {

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    private String username;
}
