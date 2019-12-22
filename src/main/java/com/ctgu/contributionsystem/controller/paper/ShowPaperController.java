package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @ResponseBody
    @RequestMapping("/get")
    public Paper getPaperById(@RequestParam("paperId") Integer paperId){
        Paper paper = null;
        /**
         * 如果paperId
         */
        if(paperId == null){
            return paper;
        }else {

        }
        return null;
    }

}
