package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: contribution-system *
 * @classname: PaperServiceImpl *
 * @author: lnback *
 * @create: 2019-12-21 15:59
 **/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperDao paperDao;

    @Override
    public int addPaper(Paper paper) {
        Paper p = paperDao.save(paper);
        return p.getPaperId();
    }
}
