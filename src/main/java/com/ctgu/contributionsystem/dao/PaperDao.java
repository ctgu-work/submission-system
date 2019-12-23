package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:58
 * @ClassName PaperDao
 * @Version 1.0.0
 */
@Transactional
public interface PaperDao extends JpaRepository<Paper, Integer> {
    Page<Paper> findAllByUserId(Pageable pageable,@Param("userId")Integer userId);

    @Modifying
    @Query(value = "update paper set status = :status where paper_id = :paperId",nativeQuery = true)
    Integer UpdateStatus(@Param("paperId") Integer paperId,@Param("status") Integer status);
}

