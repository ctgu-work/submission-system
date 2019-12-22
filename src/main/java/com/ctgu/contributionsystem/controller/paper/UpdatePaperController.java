package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: contribution-system *
 * @classname: UpdateController *
 * @author: lnback *
 * @create: 2019-12-21 16:24
 **/
@Controller
@RequestMapping("/paper")
public class UpdatePaperController {

    @RequestMapping("/update")
    @ResponseBody
    public String updatePaper(Paper paper){
        return "";
    }
}
