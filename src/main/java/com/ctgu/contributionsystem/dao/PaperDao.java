package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    @Query(nativeQuery = true,value = "SELECT tag_detail as tagDetail FROM paper a , tag b , paper_tag c WHERE a.paper_id = c.paper_id and c.tag_id = b.tag_id and a.user_id = :userId GROUP BY b.tag_detail ORDER BY count(*) DESC LIMIT 10")
    List<String> getUserHotTagsName(@Param("userId")Integer userId);
    @Query(nativeQuery = true,value = "SELECT b.tag_id as tagId FROM paper a , tag b , paper_tag c WHERE a.paper_id = c.paper_id and c.tag_id = b.tag_id and a.user_id = :userId GROUP BY b.tag_detail ORDER BY count(*) DESC LIMIT 10")
    List<Integer> getUserHotTagsId(@Param("userId")Integer userId);

    @Query(nativeQuery = true,value = "SELECT tag_detail as tagDetail FROM paper a , tag b , paper_tag c WHERE a.paper_id = c.paper_id and c.tag_id = b.tag_id GROUP BY b.tag_detail ORDER BY count(*) DESC LIMIT 10")
    List<String> getHotTagsName();
    @Query(nativeQuery = true,value = "SELECT b.tag_id as tagId FROM paper a , tag b , paper_tag c WHERE a.paper_id = c.paper_id and c.tag_id = b.tag_id GROUP BY b.tag_detail ORDER BY count(*) DESC LIMIT 10")
    List<Integer> getHotTagsId();

    @Modifying
    @Query(value = "update paper set status = :status where paper_id = :paperId",nativeQuery = true)
    Integer UpdateStatus(@Param("paperId") Integer paperId,@Param("status") Integer status);

    @Modifying
    @Query(value = "SELECT * FROM paper\n" +
            "WHERE CONCAT(IFNULL(title,''),IFNULL(content,''),IFNULL(category,'')) \n" +
            "LIKE CONCAT('%',:name,'%')",nativeQuery = true)
    List<Paper> findAllByName(@Param("name") String name);

    List<Paper> findTop10ByOrderByClickRateDesc();

    @Query(value = "select * from paper where paper_id = :paperId",nativeQuery = true)
    Paper findByPaperId(@Param("paperId") Integer paperId);
}

