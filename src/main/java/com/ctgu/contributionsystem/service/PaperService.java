package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.dto.Article;
import com.ctgu.contributionsystem.model.Paper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:59
 * @ClassName PaperService
 * @Version 1.0.0
 */
public interface PaperService {
    /**
     * 增加新文章
     * @param paper
     * @return
     */
    int addPaper(Paper paper);

    List<Paper> findAllByUserId(@Param("userId") Integer userId);
    //用户点击量
    int countUserClickRate(@Param("userId")Integer userId);
    //用户喜欢数量
    int countUserLike(@Param("userId")Integer userId);
    //用户待审核稿件
    int countWaitAccept(@Param("userId")Integer userId);
    //用户审核通过数量
    int countArticleAcceptNumber(@Param("userId")Integer userId);
    //用户审核未通过数量
    int countArticleNotAcceptNumber(@Param("userId")Integer userId);
    //用户热门标签
    List<String> getUserHotTagsName(@Param("userId")Integer userId);
    List<Integer> getUserHotTagsId(@Param("userId")Integer userId);

    /**
     * 通过paperId得到paper
     * @param paperId
     * @return
     */
    Article getPaperByPaperId(Integer paperId);

    /**
     * 返回点赞数
     * @param paperId
     * @return
     */
    Integer getLikeCountByPaperId(Integer paperId);

    /**
     * 增加点赞数
     * @param paperId
     * @return
     */
    Integer addLikeCountByPaperId(Integer paperId);

    /**
     * 修改稿件
     * @param paper
     * @return
     */
    int updatePaper(Paper paper);

    /**
     * 得到Paper
     * @return
     */
    Paper getPaper(Integer paperId);

    //首页热门标签
    List<String> getHotTagsName();
    List<Integer> getHotTagsId();

    List<Paper> findAllByName(@Param("name") String name);
    List<Paper> findTop10ByOrderByClickRateDesc();

    List<Object[]> findIndexArticles();
    List<Object[]> findIndexArticlesByCategory(@Param("Category")Integer Category);
    List<Object[]> findIndexArticlesByTagId(@Param("tagId")Integer tagId);

    Integer countAllPaper();
    List<Object[]> findIndexArticlesIn(@Param("name") String name);
}

