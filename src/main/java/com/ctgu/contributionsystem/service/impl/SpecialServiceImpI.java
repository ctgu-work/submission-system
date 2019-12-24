package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.*;
import com.ctgu.contributionsystem.dto.PaperVo;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SpecialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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

    @Autowired
    private PaperVoDao paperVoDao;

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
    public List<Paper> findAllById(Integer specialistId){
        return paperDao.findAllPaperById(specialistId);
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
    public PaperVo findByPaperId(Integer paperId){
           PaperVo paperVo = new PaperVo();
//           Object row = paperVoDao.findByPaperId(paperId);
            List _list = paperVoDao.findByPaperId(paperId);
           for (Object row : _list) {
               Object[] cells = (Object[]) row;
               paperVo.setPaperId((Integer) cells[0]);
               paperVo.setTitle((String) cells[1]);
               paperVo.setContent((String) cells[2]);
               paperVo.setAuthor((String) cells[3]);
               paperVo.setCategory((Integer) cells[4]);
               paperVo.setClickRate((Integer) cells[5]);
               paperVo.setLikeCount((Integer) cells[6]);
               paperVo.setStatus((Integer) cells[7]);
               paperVo.setUserId((Integer) cells[8]);
               paperVo.setSubmitTime((Timestamp) cells[9]);
               paperVo.setDescription((String) cells[10]);
               paperVo.setAvatar_url((String) cells[11]);

           }
           return paperVo;
    }
}
