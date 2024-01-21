package com.backend.testManagement.repository;

import com.backend.testManagement.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, String> {

}
