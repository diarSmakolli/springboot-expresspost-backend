package com.backend.testManagement.services;

import com.backend.testManagement.dto.UserDTO;
import com.backend.testManagement.dto.UserDTOSave;
import com.backend.testManagement.dto.UserLoginDTO;
import com.backend.testManagement.dto.UserSaveDTO;

public interface UserService {

    void createUser(UserSaveDTO userSaveDTO);
    String loginUser(UserLoginDTO userLoginDTO);
}
