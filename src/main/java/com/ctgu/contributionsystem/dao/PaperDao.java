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

    //用户点击量
//    @Modifying
    @Query(nativeQuery = true,value = "SELECT sum(click_rate) FROM paper where paper.user_id = :userId")
    int countUserClickRate(@Param("userId")Integer userId);
    //用户喜欢数量
//    @Modifying
    @Query(nativeQuery = true,value = "SELECT sum(like_count) FROM paper where paper.user_id = :userId")
    int countUserLike(@Param("userId")Integer userId);
    //用户待审核稿件
//    @Modifying
    @Query(nativeQuery = true,value = "SELECT count(*) FROM paper where user_id = :userId and status = 1")
    int countWaitAccept(@Param("userId")Integer userId);

    @Query(nativeQuery = true,value = "SELECT count(*) FROM paper , review_paper where paper.paper_id = review_paper.paper_id and review_paper.status = 1 and paper.user_id = :userId")
    int countArticleAcceptNumber(@Param("userId")Integer userId);

    @Query(nativeQuery = true,value = "SELECT count(*) FROM paper , review_paper where paper.paper_id = review_paper.paper_id and review_paper.status = 2 and paper.user_id = :userId")
    int countArticleNotAcceptNumber(@Param("userId")Integer userId);

}

