package com.ctgu.contributionsystem.controller.paper;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.PaperTagService;
import com.ctgu.contributionsystem.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String addPaper(@RequestParam("editorContent") String content,
                           @RequestParam("title") String title,
                           @RequestParam("category") Integer category,
                           @RequestParam("userId") Integer userId,
                           @RequestParam("author") String author,
                           @RequestParam(required = false,value = "tags") List<String> tags) {
        /**
         * 稿子为空时直接返回false,
         *
         */
        if (content == null || title == null || category == null || author == null) {
            return "false";
        }
        /**
         * 将稿子加入到数据库中
         */
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setCategory(category);
        paper.setAuthor(author);
        paper.setContent(content);
        paper.setStatus(1);
        paper.setUserId(userId);
        int paperId = paperService.addPaper(paper);
        /**
         * 将标签添加到
         */
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
