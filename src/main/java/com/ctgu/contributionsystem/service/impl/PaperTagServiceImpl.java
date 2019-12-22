package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.PaperTagDao;
import com.ctgu.contributionsystem.model.PaperTag;
import com.ctgu.contributionsystem.service.PaperTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: contribution-system *
 * @classname: PaperTagServiceImpl *
 * @author: lnback *
 * @create: 2019-12-21 20:28
 **/
@Service
public class PaperTagServiceImpl implements PaperTagService {

    @Autowired
    private PaperTagDao paperTagDao;

    @Override
    public int addPaperTag(int paperId, int tagId) {
        PaperTag pt = new PaperTag();
        pt.setPaperId(paperId);
        pt.setTagId(tagId);
        return paperTagDao.save(pt).getPaperTagId();
    }
}
