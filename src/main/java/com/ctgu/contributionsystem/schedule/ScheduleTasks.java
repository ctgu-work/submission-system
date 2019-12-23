package com.ctgu.contributionsystem.schedule;

import com.ctgu.contributionsystem.dao.PaperDao;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: contribution-system *
 * @classname: ScheduleTasks *
 * @author: lnback *
 * @create: 2019-12-23 13:58
 **/

@Component
@Slf4j
public class ScheduleTasks {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PaperDao paperDao;

    //秒 分 小时 月份中的日期 月份 星期中的日期 年份
    @Scheduled(cron = "0 0/20 * * * ?")
    public void syncClick(){
        log.info("======================开始 同步文章访问量======================");
        Long startTime = System.nanoTime();
        String key = "click_rate";
        String field = key + "_id_";
        Map<Object, Object> map = redisUtils.hashGetAll(key);
        for(Map.Entry<Object, Object> entry : map.entrySet()){
            String s = (String) entry.getKey();
            Integer paperId = Integer.parseInt(s.replaceFirst(field,""));
            Integer click = (Integer) entry.getValue();
            paperDao.updateClickRateByPaperId(paperId,click);
        }
        Long endTime = System.nanoTime();
        log.info("本次文章访问量同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
        log.info("======================结束 文章访问量结束======================");
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void syncLikeCount(){
        log.info("======================开始 同步文章点赞数======================");
        Long startTime = System.nanoTime();
        String key = "like_count";
        String field = key + "_id_";
        Map<Object, Object> map = redisUtils.hashGetAll(key);
        for(Map.Entry<Object, Object> entry : map.entrySet()){
            String s = (String) entry.getKey();
            Integer paperId = Integer.parseInt(s.replaceFirst(field,""));
            Integer likeCount = (Integer) entry.getValue();
            paperDao.updateLikeCountByPaperId(paperId,likeCount);
        }
        Long endTime = System.nanoTime();
        log.info("本次文章点赞数撒同步成功, 总耗时: {}", (endTime - startTime) / 1000000 + "ms");
        log.info("======================结束 文章点赞数结束======================");
    }
}
