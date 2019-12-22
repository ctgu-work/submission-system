package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Tag;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.PaperTagService;
import com.ctgu.contributionsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
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
    public String addPaper(Paper paper,@RequestBody() List<String> tags){
        /**
         * 稿子为空时直接返回false,
         *
         */
        System.out.println(paper);
        if(paper == null){
            return "false";
        }
        /**
         * 将稿子加入到数据库中
         */
        int paperId = paperService.addPaper(paper);
        Iterator<String> iterator = tags.iterator();
        while (iterator.hasNext()){
            String tag = iterator.next();
            System.out.println(tag);
            int tagId = tagService.getTagId(tag);
            Integer paperTagId = paperTagService.addPaperTag(paperId,tagId);
            System.out.println(paperTagId);
        }
        return "true";
    }
}
