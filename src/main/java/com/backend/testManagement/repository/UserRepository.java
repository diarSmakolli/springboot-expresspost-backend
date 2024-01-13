package com.backend.testManagement.repository;

import com.backend.testManagement.model.Test;
import com.backend.testManagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
