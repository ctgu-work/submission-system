package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.dto.ArticleTemp;
import com.ctgu.contributionsystem.dto.PaperVo;
import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.Tuple;
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
    List<Paper> findAllByUserId(@Param("userId")Integer userId);

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

    /**
     * 返回点赞数
     * @return
     */
    @Query(nativeQuery = true,value = "SELECT like_count FROM paper WHERE paper_id = :paperId")
    int getLikeCountByPaperId(@RequestParam("paperId") Integer paperId);

    /**
     * 修改访问量
     * @param paperId
     * @param clickRate
     * @return
     */
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE paper SET click_rate = click_rate + :clickRate WHERE paper_id = :paperId")
    void updateClickRateByPaperId(@RequestParam("paperId") Integer paperId,@RequestParam("clickRate") Integer clickRate);

    /**
     * 修改点赞数
     * @param paperId
     * @param likeCount
     */
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE paper SET like_count = like_count + :likeCount WHERE paper_id = :paperId")
    void updateLikeCountByPaperId(@RequestParam("paperId") Integer paperId,@RequestParam("likeCount") Integer likeCount);

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

//    List<Paper> findTop10ByOrderByClickRateDesc();
    //增加状态判断
    @Query(value = "select * from paper where paper.paper_id in(\n" +
            "\tSELECT review_paper.paper_id from review_paper where review_paper.status = 1\n" +
            ") order by paper.click_rate desc limit 10",nativeQuery = true)
    List<Paper> findTop10ByOrderByClickRateDesc();

    @Query(value = "SELECT DISTINCT a.paper_id as id , a.title as title , a.content as content ,b.avatar_url as avatarUrl ,a.author , a.submit_time as submitTime , c.category_detail as classify , a.click_rate as click , a.like_count as likeCount FROM paper a , user b , paper_category c , paper_status d , review_paper e where a.user_id = b.user_id and a.category = c.category_id and a.paper_id = e.paper_id and e.status = 1 order by a.submit_time desc",nativeQuery = true)
    List<Object[]> findIndexArticles();

    @Query(value = "SELECT DISTINCT a.paper_id as id , a.title as title , a.content as content ,b.avatar_url as avatarUrl ,a.author , a.submit_time as submitTime , c.category_detail as classify , a.click_rate as click , a.like_count as likeCount FROM paper a , user b , paper_category c , paper_status d , review_paper e where a.user_id = b.user_id and a.category = c.category_id and a.paper_id = e.paper_id and e.status = 1 and a.category = :Category order by a.submit_time desc",nativeQuery = true)
    List<Object[]> findIndexArticlesByCategory(@Param("Category")Integer Category);

    @Query(value = "SELECT DISTINCT a.paper_id as id , a.title as title , a.content as content ,b.avatar_url as avatarUrl ,a.author , a.submit_time as submitTime , c.category_detail as classify , a.click_rate as click , a.like_count as likeCount FROM paper a , user b , paper_category c , paper_status d , review_paper e , tag f , paper_tag g where a.user_id = b.user_id and a.category = c.category_id and a.paper_id = e.paper_id and e.status = 1 and f.tag_id = g.tag_id and g.paper_id = a.paper_id and f.tag_id = :tagId order by a.submit_time desc",nativeQuery = true)
    List<Object[]> findIndexArticlesByTagId(@Param("tagId")Integer tagId);

    @Query(value = "SELECT DISTINCT \n" +
            "a.paper_id as id , a.title as title , a.content as content ,b.avatar_url as avatarUrl ,a.author , a.submit_time as submitTime , c.category_detail as classify , a.click_rate as click , a.like_count as likeCount,b.email as email \n" +
            "FROM \n" +
            "paper a , user b , paper_category c , paper_status d , review_paper e \n" +
            "where \n" +
            "a.user_id = b.user_id and a.category = c.category_id and e.paper_id = :paperId and a.paper_id = :paperId and e.status = 1",nativeQuery = true)
    List<Object[]> findPaperBySomeCondition(@Param(("paperId")) Integer paperId);


    @Query(value = "select * from paper where paper_id = :paperId",nativeQuery = true)
    Paper findByPaperId(@Param("paperId") Integer paperId);

    @Query(value = "SELECT paper.* FROM paper,review_paper where paper.paper_id = review_paper.paper_id and review_paper.specialist_id = :specialistId",nativeQuery = true)
    List<Paper> findAllPaperById(@Param("specialistId") Integer specialistId);

    @Query(value = "select count(*) from paper where user_id = :userId",nativeQuery = true)
    Integer findByUserIdCount(@Param("userId") Integer userId);

    @Query(value = "SELECT DISTINCT a.paper_id as id , a.title as title , a.content as content ,b.avatar_url as avatarUrl ,a.author , a.submit_time as submitTime , c.category_detail as classify , a.click_rate as click , a.like_count as likeCount FROM paper a , user b , paper_category c , paper_status d , review_paper e where a.user_id = b.user_id and a.category = c.category_id and a.paper_id = e.paper_id and e.status = 1  and CONCAT(IFNULL(title,''),IFNULL(content,''),IFNULL(category_detail,'')) LIKE CONCAT('%',:name,'%') order by a.submit_time desc",nativeQuery = true)
    List<Object[]> findIndexArticlesIn(@Param("name") String name);
}

