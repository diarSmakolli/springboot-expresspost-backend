package com.backend.testManagement.repository;

import com.backend.testManagement.model.Porosia;
import com.backend.testManagement.model.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PorosiaRepository extends JpaRepository<Porosia, String> {
    List<Porosia> findByUserId(String userId);
    List<Porosia> findByUserIdAndShteti(String userId, String shteti);

    @Query("SELECT COUNT(p) FROM Porosia p WHERE p.user.id = :userId AND p.shteti = :shteti")
    int countPorosiaByUserIdAndShteti(@Param("userId") String userId, @Param("shteti") String shteti);

    @Query("SELECT COUNT(p) FROM Porosia p WHERE p.user.id = :userId AND p.statusi = :statusi")
    int countStatusiByUserId(@Param("userId") String userId, @Param("statusi") String statusi);


//    @Query("SELECT COALESCE(SUM(p.vleraPakos), 0) FROM Porosia p WHERE p.userid = :userId")
//    double llogaritShumenEporosive(@Param("userId") String userId);

    @Query("SELECT COALESCE(SUM(p.vleraPakos), 0) FROM Porosia p WHERE p.user.id = :userId")
    double llogaritShumenEporosive(@Param("userId") String userId);

}
