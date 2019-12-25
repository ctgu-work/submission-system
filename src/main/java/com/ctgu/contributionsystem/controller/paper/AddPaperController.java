package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.PaperTagService;
import com.ctgu.contributionsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

/**
 * @program: contribution-system *
 * @classname: addPaperController *
 * @author: lnback *
 * @create: 2019-12-21 15:57
 **/
@Controller
@RequestMapping("/paper")
public class AddPaperController {
    @Autowired
    private PaperService paperService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PaperTagService paperTagService;

    @ResponseBody
    @PostMapping("/add")
    public Integer addPaper(Paper paper,
                           String tag ) {
        /**
         * 稿子为空时直接返回false,
         *
         */
        List<String> tags = null;
        if(paper == null){
            return 0;
        }
        /**
         * 将稿子加入到数据库中
         */
        paper.setStatus(1);
        int paperId = paperService.addPaper(paper);
        /**
         * 将标签添加到关联表中
         */
        for (String item : tags){
            int tagId = tagService.getTagId(item);
            Integer paperTagId = paperTagService.addPaperTag(paperId, tagId);
        }
        return 1;
    }

}
