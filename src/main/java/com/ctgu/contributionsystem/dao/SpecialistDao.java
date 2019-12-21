package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/


@Transactional
public interface SpecialistDao extends JpaRepository<Specialist, Integer>{

    Specialist findByuserId(Integer userId);

}
