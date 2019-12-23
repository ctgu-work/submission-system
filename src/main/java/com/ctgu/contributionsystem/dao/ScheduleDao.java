package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface ScheduleDao extends JpaRepository<Paper, Integer> {

    @Modifying
    @Query(value = "SELECT * FROM paper\n" +
            "WHERE status = 1",nativeQuery = true)
    List<Paper> findAllBy();

    @Modifying
    @Query(value = "update paper set status = 3 where paper_id = :paperId",nativeQuery = true)
    Integer UpdateStatusById(@Param("paperId") Integer paperId);
}
