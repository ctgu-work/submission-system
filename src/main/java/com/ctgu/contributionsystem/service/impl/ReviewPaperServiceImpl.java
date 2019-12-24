package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.ReviewPaperDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.ReviewPaperService;
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
public class ReviewPaperServiceImpl implements ReviewPaperService {
    @Autowired
    private ReviewPaperDao reviewPaperDao;

    @Override
    public ReviewPaper findByPaperId(Integer paperId) {
        return reviewPaperDao.findByPaperId(paperId);
    }

    @Override
    public Integer findBySpecailistIdCount(Integer specialistId){
        return reviewPaperDao.findBySpecailistIdCount(specialistId);
    }
}
