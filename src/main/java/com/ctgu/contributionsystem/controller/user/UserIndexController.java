package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.dto.ArticleStatus;
import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.dto.UserIndex;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.PaperService;
import com.ctgu.contributionsystem.service.ReviewPaperService;
import com.ctgu.contributionsystem.service.SpecialService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 个人信息主页
 * @Author wh_lan
 * @create 2019-12-20 20:26
 * @ClassName IndexController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserIndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private PaperService paperService;

    @Autowired
    private SpecialService specialService;

    @Autowired
    private ReviewPaperService reviewPaperService;

    /**
     * @Author wh
     * @Description 刷新页面
     * @Date 2019/12/21 20:40
     * @Param [request]
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     **/
    @GetMapping("/")
    public ReturnResposeBody refresh(HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                String phoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(phoneNumber);
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(user);
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
            else{
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }

    /**
     * @Author wh
     * @Description 个人主页
     * @Date 2019/12/21 20:41
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/index")
    public UserIndex index(HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
                User user = userService.findByPhoneNumber(phoneNumber);
                Integer userId = user.getUserId();
                UserIndex userIndex = new UserIndex();
                int countUserLike= paperService.countUserLike(userId);
                int countUserClickRate= paperService.countUserClickRate(userId);
                int countWaitAccept= paperService.countWaitAccept(userId);
                int countUserMoney= userService.countUserMoney(userId);
                int articleAcceptNumber= paperService.countArticleAcceptNumber(userId);
                int articleNotAcceptNumber= paperService.countArticleNotAcceptNumber(userId);
                userIndex.setUserLikeCount(Integer.valueOf(countUserLike));
                userIndex.setUserClickCount(Integer.valueOf(countUserClickRate));
                userIndex.setArticleWaitNumber(Integer.valueOf(countWaitAccept));
                userIndex.setArticleMoney(Integer.valueOf(countUserMoney));
                userIndex.setArticleAcceptNumber(Integer.valueOf(articleAcceptNumber));
                userIndex.setArticleNotAcceptNumber(Integer.valueOf(articleNotAcceptNumber));
                return userIndex;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    @GetMapping("/show/{userId}")
    public String articleList(@PathVariable Integer userId){
        return "1";
    }


    /**
     * @Author wh
     * @Description 查看稿件状态
     * @Date 2019/12/21 19:48
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/profile/article")
    public List<ArticleStatus> showArticleStatus(HttpServletRequest request,
                @RequestParam(value = "startPage", required = false, defaultValue = "1") Integer startPage,
                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize){
        List<ArticleStatus>articleStatuses = new LinkedList<ArticleStatus>();
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
                User user = userService.findByPhoneNumber(phoneNumber);
                Integer userId = user.getUserId();
                Page<Paper> papers = paperService.findAllByUserId(PageRequest.of(startPage - 1, pageSize) , userId);
                List<Paper> paperList = papers.getContent();
                for( Paper paper : paperList ){
                    ArticleStatus articleStatus = new ArticleStatus();
                    articleStatus.setTitle(paper.getTitle());
                    if( paper.getStatus() == 1 ){
                        articleStatus.setStatus("未审稿");
                    }
                    else{
                        ReviewPaper reviewPaper = reviewPaperService.findByPaperId(paper.getPaperId());
                        if( reviewPaper.getStatus() == 1 ) articleStatus.setStatus("通过");
                        else articleStatus.setStatus("未通过");
                        articleStatus.setReviewContent(reviewPaper.getComment());
                        articleStatus.setReviewSpecialist(specialService.findNameBySpecialistId(reviewPaper.getSpecialistId()));
                    }
                    articleStatuses.add(articleStatus);
                }
                return articleStatuses;
            }
            else{
                return articleStatuses;
            }
        }
        catch (Exception e){
            return articleStatuses;
        }
    }
}
