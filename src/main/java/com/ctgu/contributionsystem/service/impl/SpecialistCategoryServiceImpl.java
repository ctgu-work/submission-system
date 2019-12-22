package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.SpecialistCategoryDao;
import com.ctgu.contributionsystem.model.SpecialistCategory;
import com.ctgu.contributionsystem.service.SpecialistCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 15:48
 * @ClassName SpecialistCategoryServiceImpl
 * @Version 1.0.0
 */
@Service
public class SpecialistCategoryServiceImpl implements SpecialistCategoryService {
    @Autowired
    private SpecialistCategoryDao specialistCategoryDao;

    @Override
    public List<SpecialistCategory> findAll() {
        return specialistCategoryDao.findAll();
    }
}
