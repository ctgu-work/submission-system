package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperDao;
import com.ctgu.contributionsystem.dao.TagDao;
import com.ctgu.contributionsystem.dto.Article;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Tag;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 19:59
 * @ClassName PaperServiceImpl
 * @Version 1.0.0
 */
@Service
public class PaperServiceImpl implements PaperService {


    @Autowired
    private PaperDao paperDao;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TagDao tagDao;


    @Override
    public List<Paper> findAllByUserId( Integer userId) {
        return paperDao.findAllByUserId( userId);
    }

    @Override
    public int countUserClickRate(Integer userId) {
        return paperDao.countUserClickRate(userId);
    }

    @Override
    public int countUserLike(Integer userId) {
        return paperDao.countUserLike(userId);
    }

    @Override
    public int countWaitAccept(Integer userId) {
        return paperDao.countWaitAccept(userId);
    }

    @Override
    public int countArticleAcceptNumber(Integer userId) {
        return paperDao.countArticleAcceptNumber(userId);
    }

    @Override
    public int countArticleNotAcceptNumber(Integer userId) {
        return paperDao.countArticleNotAcceptNumber(userId);
    }

    @Override
    public List<String> getUserHotTagsName(Integer userId) {
        return paperDao.getUserHotTagsName(userId);
    }

    @Override
    public List<Integer> getUserHotTagsId(Integer userId) {
        return paperDao.getUserHotTagsId(userId);
    }

    @Override
    public Article getPaperByPaperId(Integer paperId) {
        /**
         * 先取出paper，获取paper未更新的访问量
         */
        List<Object[]> articleTemps = paperDao.findPaperBySomeCondition(paperId);
        Integer cnt = 0;
        Article article = new Article();
        if(articleTemps == null){
            return null;
        }
        for( Object[] object:articleTemps ){

            try {
                article.setId((Integer)object[0]);
                article.setTitle((String)object[1]);
                article.setContent((String)object[2]);
                article.setAvatarUrl((String)object[3]);
                article.setAuthor((String)object[4]);
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format((Timestamp)object[5]);
                article.setDate(sd);
                article.setClassify((String) object[6]);
                article.setClick((Integer)object[7]);
                cnt = article.getClick();
                article.setLikeCount((Integer)object[8]);
                List<Tag>tags = tagDao.findByPaperId(article.getId());
                article.setTags(tags);
            }
            catch (Exception e){
                return null;
            }
        }
        String key = "click_rate";
        String field = key + "_id_" + paperId;
        if (redisUtils.hasKey(key)) {
            Integer redisCnt = (Integer) redisUtils.hashGet(key, field);
            if (redisCnt == null) {
                redisUtils.hashSet(key, field, 1);
                redisCnt = 1;
            } else {
                redisUtils.hashSet(key, field, redisCnt + 1);
            }
            cnt = cnt + redisCnt;
        } else {
            redisUtils.hashSet(key, field, 1);
            redisUtils.expire(key, 60 * 20);
            cnt++;
        }
        article.setClick(cnt);
        return article;
    }

    @Override
    public Integer getLikeCountByPaperId(Integer paperId) {
        Integer likeCount = paperDao.getLikeCountByPaperId(paperId);
        String key = "like_count";
        String field = key + "_id_" + paperId;
        /**
         * 检查redis中是否存储了hash
         */
        if (redisUtils.hasKey(key)) {
            Integer redisCnt = (Integer) redisUtils.hashGet(key, field);
            if (redisCnt == null) {
                redisCnt = 0;
            }
            likeCount = redisCnt + likeCount;
        }
        return likeCount;
    }

    @Override
    public Integer addLikeCountByPaperId(Integer paperId) {
        /**
         * 先读取数据库中的数据
         */
        Integer likeCount = paperDao.getLikeCountByPaperId(paperId);

        String key = "like_count";
        String field = key + "_id_" + paperId;
        /**
         * 检查redis中是否存储了hash
         */
        if (redisUtils.hasKey(key)) {
            /**
             * 有hash的话直接取entry
             *  再判断entry是否为空
             *  空 ：往hash里插入
             *  非空 ：值+1
             * 没有hahs
             *  重新建立，并设置时间。
             */
            Integer redisCnt = (Integer) redisUtils.hashGet(key, field);
            if (redisCnt == null) {
                redisUtils.hashSet(key, field, 1);
                redisCnt = 1;
            } else {
                redisUtils.hashSet(key, field, redisCnt + 1);
            }
            likeCount = redisCnt + likeCount;
        } else {
            redisUtils.hashSet(key, field, 1);
            redisUtils.expire(key, 20 * 60);
            likeCount += 1;
        }
        return likeCount;
    }

    @Override
    public int updatePaper(Paper paper) {
        Paper p = paperDao.save(paper);
        return p.getPaperId();
    }

    @Override
    public Paper getPaper(Integer paperId) {
        return paperDao.getOne(paperId);
    }


    @Override
    public int addPaper(Paper paper) {
        Paper p = paperDao.save(paper);
        return p.getPaperId();
    }

    @Override
    public List<String> getHotTagsName() {
        return paperDao.getHotTagsName();
    }

    @Override
    public List<Integer> getHotTagsId() {
        return paperDao.getHotTagsId();
    }

    @Override
    public List<Paper> findAllByName(String name){
        return paperDao.findAllByName(name);
    }

    @Override
    public List<Paper> findTop10ByOrderByClickRateDesc() {
        return paperDao.findTop10ByOrderByClickRateDesc();
    }

    @Override
    public List<Object[]> findIndexArticlesIn(String name) {
        return paperDao.findIndexArticlesIn(name);
    }

    @Override
    public List<Object[]> findIndexArticles() {
        return paperDao.findIndexArticles();
    }

    @Override
    public List<Object[]> findIndexArticlesByCategory(Integer Category) {
        return paperDao.findIndexArticlesByCategory(Category);
    }

    @Override
    public List<Object[]> findIndexArticlesByTagId(Integer tagId) {
        return paperDao.findIndexArticlesByTagId(tagId);
	}
	@Override
    public Integer countAllPaper() {
        return (int) paperDao.count();

    }

}