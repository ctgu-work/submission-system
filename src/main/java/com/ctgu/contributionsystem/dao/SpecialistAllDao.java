package com.ctgu.contributionsystem.dao;


import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface SpecialistAllDao extends JpaRepository<Paper, String> {

    @Query(value = "select * from paper a where exists(select category from specialist where category = a.category) ORDER BY a.click_rate DESC",nativeQuery=true)
    Page<Paper> findAll(Pageable pageable);
}
