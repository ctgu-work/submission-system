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
    public String updatePaper(
            @RequestParam("paperId") Integer paperId,
            @RequestParam("editorContent") String content,
            @RequestParam("title") String title,
            @RequestParam("category") Integer category,
            @RequestParam("userId") Integer userId,
            @RequestParam("author") String author,
            @RequestParam("tags") List<String> tags) {

        if (paperId == null) {
            return "false";
        }
        Paper paper = new Paper();
        paper.setPaperId(paperId);
        paper.setContent(content);
        paper.setAuthor(author);
        paper.setCategory(category);
        paper.setTitle(title);
        paper.setUserId(userId);
        paper.setStatus(1);
        int pId = paperService.updatePaper(paper);

        Iterator<String> iterator = tags.iterator();
        while (iterator.hasNext()) {
            String tag = iterator.next();
            System.out.println(tag);
            int tagId = tagService.getTagId(tag);
            Integer paperTagId = paperTagService.addPaperTag(paperId, tagId);
            System.out.println(paperTagId);
        }
        return "true";
    }
}
