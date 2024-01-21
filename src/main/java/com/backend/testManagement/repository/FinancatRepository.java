package com.backend.testManagement.repository;

import com.backend.testManagement.model.Financat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FinancatRepository extends JpaRepository<Financat, String> {

}
