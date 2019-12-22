package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
}