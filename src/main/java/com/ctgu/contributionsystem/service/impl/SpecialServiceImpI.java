package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperReviewDao;
import com.ctgu.contributionsystem.dao.SpecialistAllDao;
import com.ctgu.contributionsystem.dao.SpecialistDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/
@Service
public class SpecialServiceImpI implements SpecialService {

    @Autowired
    private SpecialistAllDao specialistAllDao;

    @Autowired
    private PaperReviewDao paperReviewDao;

    @Autowired
    private SpecialistDao specialistDao;

    @Override
    public Specialist findByUserId(Integer userId) {
        return specialistDao.findByuserId(userId);
    }


    @Override
    public List<Paper> findAll(Integer category){
        return specialistAllDao.findAll(category);

    }


    @Override
    public ReviewPaper addReviewPaper1(ReviewPaper reviewPaper) {
        return paperReviewDao.save(reviewPaper);
    }
}
