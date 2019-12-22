package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.SpecialistCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-21 15:47
 * @ClassName SpecialistCategoryDao
 * @Version 1.0.0
 */
@Transactional
public interface SpecialistCategoryDao extends JpaRepository<SpecialistCategory, Integer> {
    @Override
    List<SpecialistCategory> findAll();
}
