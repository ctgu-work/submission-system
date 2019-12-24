package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuperAdminService {
    List<Admin> findAll();

    int findAdminBy(@Param("adminId") Integer adminId);

    Admin addAdmin(Admin admin1);

    Admin updateAdmin(Admin admin1);

    void delete(Integer adminId);
}
