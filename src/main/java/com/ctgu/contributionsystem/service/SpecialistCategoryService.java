package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.SpecialistCategory;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 15:48
 * @ClassName SpecialistCategoryService
 * @Version 1.0.0
 */
public interface SpecialistCategoryService {
    List<SpecialistCategory> findAll();
}
