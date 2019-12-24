package com.ctgu.contributionsystem.controller.specialist;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.ReviewPaper;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SpecialService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.stream.events.Comment;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@Controller
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    private SpecialService specialService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;


    //专家登录
    @PostMapping("/login")
    @ResponseBody
    public ReturnResposeBody SpecialistLogin(@RequestParam("phoneNumber")String phoneNumber ,
                            @RequestParam("password")String password){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");

        ReturnResposeBody returnResposeBody2 = new ReturnResposeBody();
        returnResposeBody2.setMsg("您已被禁用");
        returnResposeBody2.setStatus("200");

        User user = userService.findByPhoneNumber(phoneNumber);

        String token = JwtUtil.sign(phoneNumber, Md5Salt.Md5SaltCrypt(password));
        if(user.getPassword().equals(Md5Salt.Md5SaltCrypt(password))){
            try {
                Specialist specialist = specialService.findByUserId(user.getUserId());
                if (specialist.getStatus() == 2) {
                    System.out.println(specialist);
                    if(specialist.getProhibit() == 1){
                        redisUtils.set(phoneNumber, token, 60 * 60);
                        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
                        returnResposeBody.setMsg("success");
                        returnResposeBody.setResult(user);
                        returnResposeBody.setJwtToken(token);
                        returnResposeBody.setStatus("200");
                        return returnResposeBody;
                    }
                    else{
                        return returnResposeBody2;
                    }
                } else {
                    returnResposeBody1.setMsg("未审核");
                    returnResposeBody1.setStatus("200");
                    return returnResposeBody1;
                }
            }catch (Exception e) {
                return returnResposeBody1;
            }
        }
        return returnResposeBody1;

    }

    //登出
    @GetMapping("/logout")
    @ResponseBody
    public String SpecialistLogout(HttpServletRequest request){
            //从请求头中获取token
            String token = request.getHeader("token");
             System.out.println(token);
            //中介储存
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            //shiro登录判断
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
                //删除token
                redisUtils.del(phoneNumber);
                return "1";
            }
            else{
                return "0";
            }
        }

    //全部未审稿稿件
    @GetMapping("/paperlist")
    @ResponseBody
    public ReturnResposeBody SpecialistFindAll(HttpServletRequest request, @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10",name = "size") Integer size){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        //shiro登录判断
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if(subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
         try {
             User user = userService.findByPhoneNumber(phoneNumber);
             try {
                 Specialist specialist = specialService.findByUserId(user.getUserId());
                 List<Paper> allPicturesPage = specialService.findAll(specialist.getCategory());
                 JpaPageHelper jpaPageHelper = new JpaPageHelper();
                 List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(allPicturesPage,pageNum,size);
                 returnResposeBody.setMsg("success");
                 returnResposeBody.setStatus("200");
                 returnResposeBody.setResult(pageInfos);
                 return returnResposeBody;
             } catch (Exception e) {
                 returnResposeBody.setMsg("查询失败");
                 returnResposeBody.setStatus("200");
                 return returnResposeBody;
             }
         }catch (Exception e){
             returnResposeBody.setMsg("获取number失败");
             returnResposeBody.setStatus("200");
             return returnResposeBody;
         }
}
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }


    @GetMapping("/paperlistAl")
    @ResponseBody
    public ReturnResposeBody SpecialistFindAllAl(HttpServletRequest request, @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "10",name = "size") Integer size) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        //shiro登录判断
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if(subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)) {
            try {
                User user = userService.findByPhoneNumber(phoneNumber);
                try {
                    Specialist specialist = specialService.findByUserId(user.getUserId());
                    List<Paper> allPicturesPage = specialService.findAllById(specialist.getSpecialistId());
                    JpaPageHelper jpaPageHelper = new JpaPageHelper();
                    List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(allPicturesPage, pageNum, size);
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    returnResposeBody.setResult(pageInfos);
                    return returnResposeBody;
                } catch (Exception e) {
                    returnResposeBody.setMsg("查询失败");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                }
            } catch (Exception e) {
                returnResposeBody.setMsg("获取number失败");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }

    //查看稿件
    @GetMapping("/findPaper")
    @ResponseBody
    public ReturnResposeBody FindPaper(@Param("paperId") Integer paperId,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        //shiro登录判断
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if(subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)) {
            try {
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                Paper paper = specialService.findByPaperId(paperId);
                returnResposeBody.setResult(paper);
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setMsg("未找到");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //审稿
    @PostMapping("/PeerReview")
    @ResponseBody
   public ReturnResposeBody PeerReview(@RequestParam("paperId") Integer paperId,
                            @RequestParam("comment") String comment,
                            @RequestParam("status") Integer status,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        User user = userService.findByPhoneNumber(phoneNumber);
        if(subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)) {
            try {
                Specialist specialist = specialService.findByUserId(user.getUserId());
                try {
                    ReviewPaper reviewPaper1 = new ReviewPaper();
                    reviewPaper1.setSpecialistId(specialist.getSpecialistId());
                    reviewPaper1.setStatus(status);
                    reviewPaper1.setComment(comment);
                    reviewPaper1.setPaperId(paperId);
                    specialService.addReviewPaper1(reviewPaper1);
                    Integer s = specialService.Updatestatus(paperId, status);
                    if (s != null) {
                        returnResposeBody.setMsg("审核成功");
                        returnResposeBody.setStatus("200");
                        return returnResposeBody;
                    } else {
                        returnResposeBody.setMsg("审核失败");
                        returnResposeBody.setStatus("200");
                        return returnResposeBody;
                    }

                } catch (Exception e) {
                    returnResposeBody.setMsg("专家未登陆");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                }
            }catch (Exception e){
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }

        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
   }
}
