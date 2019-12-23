package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.kerberos.KerberosTicket;

/**
 * @program: contribution-system *
 * @classname: ShowPaperController *
 * @author: lnback *
 * @create: 2019-12-21 16:19
 **/
@Controller
@RequestMapping("/paper")
public class ShowPaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private RedisUtils redisUtils;


    @ResponseBody
    @RequestMapping("/get")
    public Paper getPaperById(@RequestParam("paperId") Integer paperId){
        Paper paper = null;
        /**
         * 如果paperId不存在
         */
        if(paperId == null){
            return paper;
        }else {
            paper = paperService.getPaperByPaperId(paperId);
            Integer likeCount = paperService.getLikeCountByPaperId(paperId);
            paper.setLikeCount(likeCount);
        }
        return paper;
    }

}
