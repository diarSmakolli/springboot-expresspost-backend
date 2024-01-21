package com.backend.testManagement.services;

import com.backend.testManagement.dto.UserDTO;
import com.backend.testManagement.dto.UserDTOSave;
import com.backend.testManagement.dto.UserLoginDTO;
import com.backend.testManagement.dto.UserSaveDTO;
import com.backend.testManagement.model.User;

import java.util.List;

public interface UserService {

    void createUser(UserSaveDTO userSaveDTO);
    String loginUser(UserLoginDTO userLoginDTO);

    List<User> getAllUsers();
}
