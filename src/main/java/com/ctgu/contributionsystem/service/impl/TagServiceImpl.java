package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.TagDao;
import com.ctgu.contributionsystem.model.Tag;
import com.ctgu.contributionsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: contribution-system *
 * @classname: TagServiceImpl *
 * @author: lnback *
 * @create: 2019-12-21 18:27
 **/
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;
    @Override
    public List<Tag> findByPaperId(Integer PaperId) {
        return tagDao.findByPaperId(PaperId);
	}
	
    @Override
    public int getTagId(String tag) {
        Tag t = tagDao.findBytagDetail(tag);
        if(t == null){
            t = new Tag();
            t.setTagDetail(tag);
            t = tagDao.save(t);
            return t.getTagId();
        }else {
            return t.getTagId();
        }
    }
}

