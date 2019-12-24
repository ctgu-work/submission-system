package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.PaperTagService;
import com.ctgu.contributionsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

/**
 * @program: contribution-system *
 * @classname: UpdateController *
 * @author: lnback *
 * @create: 2019-12-21 16:24
 **/
@Controller
@RequestMapping("/paper")
public class UpdatePaperController {

    @Autowired
    private PaperService paperService;

    @Autowired
    private PaperTagService paperTagService;

    @Autowired
    private TagService tagService;

    @ResponseBody
    @RequestMapping("/update")
    public Integer updatePaper(Paper paper,
                              @RequestParam(required = false,value = "tags") List<String> tags) {

        /**
         * 如果paper为空，返回错误
         */
        if(paper == null){
            return 0;
        }
        /**
         * 获取paperId
         */
        int paperId = paper.getPaperId();
        /**
         *
         */
        Paper p2 = paperService.getPaper(paperId);
        p2.setUserId(paper.getUserId());p2.setAuthor(paper.getAuthor());p2.setContent(paper.getContent());
        p2.setCategory(paper.getCategory());p2.setTitle(paper.getTitle());p2.setSubmitTime(new Timestamp(System.currentTimeMillis()));

        paperTagService.deleteAllPaperTagByPaperId(paperId);

        for (String tag : tags){
            int tagId = tagService.getTagId(tag);
            Integer paperTagId = paperTagService.addPaperTag(paperId, tagId);
        }
        return 1;
    }
}
