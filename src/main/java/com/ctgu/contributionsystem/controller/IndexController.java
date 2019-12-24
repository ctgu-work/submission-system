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
import com.ctgu.contributionsystem.utils.JpaPageHelper;
import com.ctgu.contributionsystem.utils.PageInfo;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.persistence.Tuple;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     * @Author wh
     * @Description 首页最热话题
     * @Date 2019/12/22 20:53
     * @Param []
     **/
    @GetMapping("/hottag")
    public ReturnResposeBody hotTags() {
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
        } catch (Exception e) {
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }


    /**
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     * @Author wh
     * @Description 主页最热文章
     * @Date 2019/12/23 19:16
     * @Param []
     **/
    @GetMapping("/hotartcle")
    public ReturnResposeBody hotArtcle() {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try {
            List<Paper> paperList = paperService.findTop10ByOrderByClickRateDesc();
            List<HotArtcle> list = new LinkedList<>();
            for (Paper paper : paperList) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format(paper.getSubmitTime());
                HotArtcle artcle = new HotArtcle(paper.getPaperId(), sd, paper.getTitle(), paper.getClickRate(), paper.getLikeCount());
                list.add(artcle);
            }
            returnResposeBody.setResult(list);
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("success");
            return returnResposeBody;
        } catch (Exception e) {
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }


    /**
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     * @Author wh
     * @Description 首页文章列表
     * @Date 2019/12/24 16:04
     * @Param [request, startPage, pageSize]
     **/
    @GetMapping("/articlelist")
    public ReturnResposeBody articlelist(HttpServletRequest request,
                                         @RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
                                         @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        List<Object[]> articleTemps = paperService.findIndexArticles();
        List<Article> articles = new ArrayList<>();
        for (Object[] object : articleTemps) {
            Article article = new Article();
            try {
                article.setId((Integer) object[0]);
                article.setTitle((String) object[1]);
                article.setContent((String) object[2]);
                article.setAvatarUrl((String) object[3]);
                article.setAuthor((String) object[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format((Timestamp) object[5]);
                article.setDate(sd);
                article.setClassify((String) object[6]);
                article.setClick((Integer) object[7]);
                article.setLikeCount((Integer) object[8]);
                List<Tag> tags = tagService.findByPaperId(article.getId());
                article.setTags(tags);
                articles.add(article);
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        JpaPageHelper jpaPageHelper = new JpaPageHelper();
        List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(articles, startPage, pageSize);
        returnResposeBody.setResult(pageInfos);
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("success");
        return returnResposeBody;
    }

    /**
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     * @Author wh
     * @Description 根据分类查询文章
     * @Date 2019/12/24 16:12
     * @Param [request, category, startPage, pageSize]
     **/
    @GetMapping("/categoryArticlelist")
    public ReturnResposeBody getArticlelistByCategory(HttpServletRequest request, @RequestParam("category") Integer category,
                                                      @RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        List<Object[]> articleTemps = paperService.findIndexArticlesByCategory(category);
        List<Article> articles = new ArrayList<>();
        for (Object[] object : articleTemps) {
            Article article = new Article();
            try {
                article.setId((Integer) object[0]);
                article.setTitle((String) object[1]);
                article.setContent((String) object[2]);
                article.setAvatarUrl((String) object[3]);
                article.setAuthor((String) object[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format((Timestamp) object[5]);
                article.setDate(sd);
                article.setClassify((String) object[6]);
                article.setClick((Integer) object[7]);
                article.setLikeCount((Integer) object[8]);
                List<Tag> tags = tagService.findByPaperId(article.getId());
                article.setTags(tags);
                articles.add(article);
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        JpaPageHelper jpaPageHelper = new JpaPageHelper();
        List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(articles, startPage, pageSize);
        returnResposeBody.setResult(pageInfos);
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("success");
        return returnResposeBody;
    }


    /**
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     * @Author wh
     * @Description 根据标签查看文章
     * @Date 2019/12/24 16:24
     * @Param [request, tagId, startPage, pageSize]
     **/
    @GetMapping("/tagArticlelist")
    public ReturnResposeBody getArticlelistByTagId(HttpServletRequest request, @RequestParam("tagId") Integer tagId,
                                                   @RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        List<Object[]> articleTemps = paperService.findIndexArticlesByTagId(tagId);
        List<Article> articles = new ArrayList<>();
        for (Object[] object : articleTemps) {
            Article article = new Article();
            try {
                article.setId((Integer) object[0]);
                article.setTitle((String) object[1]);
                article.setContent((String) object[2]);
                article.setAvatarUrl((String) object[3]);
                article.setAuthor((String) object[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format((Timestamp) object[5]);
                article.setDate(sd);
                article.setClassify((String) object[6]);
                article.setClick((Integer) object[7]);
                article.setLikeCount((Integer) object[8]);
                List<Tag> tags = tagService.findByPaperId(article.getId());
                article.setTags(tags);
                articles.add(article);
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        JpaPageHelper jpaPageHelper = new JpaPageHelper();
        List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(articles, startPage, pageSize);
        returnResposeBody.setResult(pageInfos);
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("success");
        return returnResposeBody;
    }

    //主页搜索
    @GetMapping("/find")
    public ReturnResposeBody FindPaper(HttpRequest request, @Param("name") String name, @RequestParam(defaultValue = "1", name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10", name = "size") Integer size) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        List<Article> articles = new ArrayList<>();
        try {
            List<Object[]> articleTemps = paperService.findIndexArticlesIn(name);
            for (Object[] object : articleTemps) {
                Article article = new Article();
                article.setId((Integer) object[0]);
                article.setTitle((String) object[1]);
                article.setContent((String) object[2]);
                article.setAvatarUrl((String) object[3]);
                article.setAuthor((String) object[4]);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String sd = sdf.format((Timestamp) object[5]);
                article.setDate(sd);
                article.setClassify((String) object[6]);
                article.setClick((Integer) object[7]);
                article.setLikeCount((Integer) object[8]);
                List<Tag> tags = tagService.findByPaperId(article.getId());
                article.setTags(tags);
                articles.add(article);
            }
        } catch (Exception e) {
            returnResposeBody.setMsg("error");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
        JpaPageHelper jpaPageHelper = new JpaPageHelper();
        List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(articles, pageNum, size);
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        returnResposeBody.setResult(pageInfos);
        return returnResposeBody;
    }

}
