package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VoDao extends JpaRepository<ReviewPaper, Long> {

    @Query(value = "SELECT s.specialist_id as specialistId,a.name,COUNT(*) as count FROM review_paper s INNER JOIN specialist c ON\n" +
            "s.specialist_id =c.specialist_id INNER JOIN user a ON\n" +
            "a.user_id = c.user_id \n" +
            "GROUP BY c.specialist_id",nativeQuery = true)
    List findCountVo();

}
