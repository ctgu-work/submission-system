package com.ctgu.contributionsystem.controller;

import com.ctgu.contributionsystem.dto.Article;
import com.ctgu.contributionsystem.dto.ArticleTemp;
import com.ctgu.contributionsystem.dto.HotArtcle;
import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Tag;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.TagService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.NativeResultProcessUtils;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-20 20:54
 * @ClassName IndexController
 * @Version 1.0.0
 */
@RequestMapping("/index")
@RestController
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private PaperService paperService;

    @Autowired
    private TagService tagService;

    /**
     * @Author wh
     * @Description 首页最热话题
     * @Date 2019/12/22 20:53
     * @Param []
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     **/
    @GetMapping("/hottag")
    public ReturnResposeBody hotTags(){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try {
            List<String> list = paperService.getHotTagsName();
            List<Integer> list1 = paperService.getHotTagsId();
            List<Tag> list2 = new LinkedList<Tag>();
            for (int i = 0; i < list.size(); i++) {
                Tag tag = new Tag();
                tag.setTagId(list1.get(i));
                tag.setTagDetail(list.get(i));
                list2.add(tag);
            }
            returnResposeBody.setResult(list2);
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("success");
            return returnResposeBody;
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }


    /**
     * @Author wh
     * @Description 主页最热文章
     * @Date 2019/12/23 19:16
     * @Param []
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     **/
    @GetMapping("/hotartcle")
    public ReturnResposeBody hotArtcle(){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try {
            List<Paper>paperList = paperService.findTop10ByOrderByClickRateDesc();
            List<HotArtcle>list = new LinkedList<>();
            for( Paper paper:paperList ){
                SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(paper.getSubmitTime());
                HotArtcle artcle = new HotArtcle(paper.getPaperId() ,sd, paper.getTitle(),paper.getClickRate(),paper.getLikeCount());
                list.add(artcle);
            }
            returnResposeBody.setResult(list);
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("success");
            return returnResposeBody;
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }


    @GetMapping("/articlelist")
    public ReturnResposeBody articlelist(HttpServletRequest request,
                                         @RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        Page<ArticleTemp> articleTemps = paperService.findIndexArticles(PageRequest.of(startPage - 1, pageSize));
        List<ArticleTemp> articleTempList = articleTemps.getContent();
        List<Article>articles = new LinkedList<Article>();
        for( ArticleTemp articleTemp : articleTempList ){
            Article article = new Article();
            article.setId(article.getId());
            article.setTitle(article.getContent());
            article.setAuthor(article.getAuthor());
            article.setAvatarUrl(article.getAvatarUrl());
            article.setContent(article.getContent());
            article.setClassify(article.getClassify());
            article.setClick(article.getClick());
            article.setLikeCount(article.getLikeCount());
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String sd = sdf.format(article.getDate());
            article.setDate(sd);
            List<Tag>tags = tagService.findByPaperId(article.getId());
            article.setTags(tags);
            articles.add(article);
        }
        returnResposeBody.setResult(articles);
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("success");

        return returnResposeBody;
    }

    //主页搜索
    @GetMapping("/find")
    public List<Paper> FindPaper(HttpRequest request,@Param("name") String name){
        try {
            List<Paper> papers = paperService.findAllByName(name);
            return papers;
        } catch (Exception e){
            return null;
        }
    }


}
