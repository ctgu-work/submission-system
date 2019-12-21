package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.ReviewPaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:58
 * @ClassName PaperDao
 * @Version 1.0.0
 */
@Transactional
public interface ReviewPaperDao extends JpaRepository<ReviewPaper, Integer> {
    ReviewPaper findByPaperId(@Param("paperId")Integer paperId);
}