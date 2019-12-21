package com.ctgu.contributionsystem.dao;


import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface SpecialistAllDao extends JpaRepository<Paper, String> {

    @Query(value = "select * from paper where category  = 1 ORDER BY click_rate DESC",nativeQuery=true)
    List<Paper> findAll(Integer caregory);
}
