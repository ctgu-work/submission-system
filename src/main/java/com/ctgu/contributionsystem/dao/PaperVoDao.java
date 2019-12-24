package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.dto.PaperVo;
import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Transactional
public interface PaperVoDao  extends JpaRepository<Paper, Integer> {

    @Modifying
    @Query(value = "select paper.paper_id,paper.title,paper.content,paper.author,paper.category,paper.click_rate,paper.like_count,paper.status,paper.user_id,paper.submit_time,user.description,user.avatar_url from paper,user where paper_id = :paperId and paper.user_id = user.user_id",nativeQuery = true)
    List findByPaperId(@Param("paperId") Integer paperId);
}
