package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.ScheduleDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/
@Service
public class ScheduleServiceImpI implements ScheduleService {

    @Autowired
    private ScheduleDao scheduleDao;

    @Override
    public List<Paper> findAll(){
        return scheduleDao.findAllBy();
    }

    @Override
    public Integer UpdateById(Integer paperId){
        return scheduleDao.UpdateStatusById(paperId);
    }

}
