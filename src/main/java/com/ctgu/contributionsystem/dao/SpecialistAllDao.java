package com.ctgu.contributionsystem.dao;


import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface SpecialistAllDao extends JpaRepository<Paper, Integer> {


    @Query(value = "select * from paper where paper.category  = :category ORDER BY paper.click_rate DESC",nativeQuery=true)
    List<Paper> findAllByCategory(@Param("category") Integer Category);
}
