package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperDao;
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

    @Autowired
    private PaperDao paperDao;

    @Override
    public Specialist findByUserId(Integer userId) {
        return specialistDao.findByuserId(userId);
    }

    @Override
    public Specialist addSpecialist(Specialist specialist) {
        return specialistDao.save(specialist);
    }

    @Override
     public Integer Updatestatus(Integer paperId, Integer status){
        return paperDao.UpdateStatus(paperId,status);
    }

    @Override
    public List<Paper> findAll(Integer Category){
        return specialistAllDao.findAllByCategory(Category);

    }

    @Override
    public Page<Paper> findAll(Pageable pageable){
        return specialistAllDao.findAll(pageable);

    }

    @Override
    public ReviewPaper addReviewPaper1(ReviewPaper reviewPaper) {
        return paperReviewDao.save(reviewPaper);
    }

    @Override
    public String findNameBySpecialistId(Integer specialistId) {
        return specialistDao.findNameBySpecialistId(specialistId);
    }

    @Override
    public Paper findByPaperId(Integer paperId){
           return paperDao.findByPaperId(paperId);
    }
}
