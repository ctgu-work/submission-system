package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.Message;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

@Transactional
public interface AdminDao extends JpaRepository<Admin,Integer> {
    Admin findByPhoneNumber(String phoneNumber);


}
