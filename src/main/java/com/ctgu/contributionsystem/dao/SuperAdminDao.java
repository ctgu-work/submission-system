package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SuperAdminDao extends JpaRepository<Admin,Integer> {

    @Query(value = "Select c.rank from admin c where admin_id = :adminId",nativeQuery = true)
    int findAdminById(@Param("adminId") Integer adminId);


}
