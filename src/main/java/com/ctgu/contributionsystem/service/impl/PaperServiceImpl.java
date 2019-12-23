package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperDao;
import com.ctgu.contributionsystem.dto.ArticleTemp;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
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

    @Override
    public Page<Paper> findAllByUserId(Pageable pageable, Integer userId) {
        return paperDao.findAllByUserId(pageable, userId);
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
    public Page<ArticleTemp> findIndexArticles(Pageable pageable) {
        return paperDao.findIndexArticles(pageable);
    }

}