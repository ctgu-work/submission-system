package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;

/**
 * @program: contribution-system *
 * @classname: AddLikeCountController *
 * @author: lnback *
 * @create: 2019-12-23 10:59
 **/
@Controller
@RequestMapping("/paper")
public class AddLikeCountController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private RedisUtils redisUtils;

    @ResponseBody
    @GetMapping("/addLikeCount")
    public Integer addLikeCount(@RequestParam("paperId") Integer paperId){
        if(paperId == null){
            return null;
        }else {
            Integer likeCount = paperService.addLikeCountByPaperId(paperId);
            return likeCount;
        }
    }
}
