package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: contribution-system *
 * @classname: addPaperController *
 * @author: lnback *
 * @create: 2019-12-21 15:57
 **/
@Controller
@RequestMapping("/paper")
public class AddController {
    @Autowired
    private PaperService paperService;

    @RequestMapping("/add")
    public String addPaper(Paper paper,@RequestParam(value = "tags[]") String[] tags){
        return "";
    }
}
