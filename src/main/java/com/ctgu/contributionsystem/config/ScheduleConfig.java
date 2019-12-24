package com.ctgu.contributionsystem.config;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author : kun
 * @date ： 2019/12/23
 * @description ：this is a code
 **/

@Component
public class ScheduleConfig{

    Logger logger = LoggerFactory.getLogger(getClass());
    private int i;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private ScheduleService scheduleService;

    //定时判断过期
    @Scheduled(fixedRate = 1000*60*60*12)
    public void execute() {
         List<Paper> papers  =  scheduleService.findAll();
         for(Paper p : papers){
             Date now = new Date();
             try {
                 long stateTimeLong = now.getTime();
                 long endTimeLong = p.getSubmitTime().getTime();
                 // 结束时间-开始时间 = 天数
                 long day = (stateTimeLong-endTimeLong)/(24*60*60*1000);
                 if(day >= 30){
                     try {
                         Integer s = scheduleService.UpdateById(p.getPaperId());
                     } catch(Exception e){

                     }
                 }
             } catch (Exception e) {
                 e.printStackTrace();
             }
         }
    }

}
