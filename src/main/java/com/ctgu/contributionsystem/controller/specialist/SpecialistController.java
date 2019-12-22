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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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


    @PostMapping("/login")
    @ResponseBody
    public ReturnResposeBody SpecialistLogin(@RequestParam("phoneNumber")String phoneNumber ,
                            @RequestParam("password")String password){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");
        User user = userService.findByPhoneNumber(phoneNumber);
        String token = JwtUtil.sign(phoneNumber, Md5Salt.Md5SaltCrypt(password));
        if(user.getPassword().equals(Md5Salt.Md5SaltCrypt(password))){
            try {
                Specialist specialist = specialService.findByUserId(user.getUserId());
                if (specialist.getStatus() == 2) {
                    redisUtils.set(phoneNumber , token,60 * 60);
                    ReturnResposeBody returnResposeBody = new ReturnResposeBody();
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setResult(user);
                    returnResposeBody.setJwtToken(token);
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    return returnResposeBody1;
                }
            }catch (Exception e) {
                return returnResposeBody1;
            }
        }
        return returnResposeBody1;

    }

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

    @GetMapping("/paperlist")
    @ResponseBody
    public List<PageInfo> SpecialistFindAll(HttpServletRequest request, @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",name = "size") Integer size){
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
                 System.out.println(specialist);
//        List<Paper> list = specialService.findAll();
//        PageInfo<Paper> pageInfo = new PageInfo<Paper>(list);
//        System.out.println(pageInfo.getPageNum());
//                 System.out.println(specialist.getCategory());
//                 int a = specialist.getCategory().intValue();
//                 System.out.println(a);
                 List<Paper> allPicturesPage = specialService.findAll(specialist.getCategory());
                 JpaPageHelper jpaPageHelper = new JpaPageHelper();
                 List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(allPicturesPage,pageNum,size);
//            List<Paper> list = specialService.findAll();
                 return pageInfos;
             } catch (Exception e) {
                 return null;
             }
         }catch (Exception e){
             return null;
         }
}
        return null;
    }


    @PostMapping("/PeerReview")
    @ResponseBody
   public ReturnResposeBody PeerReview(@RequestBody ReviewPaper reviewPaper,HttpServletRequest request){
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        //shiro登录判断
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");
        System.out.println(reviewPaper);
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if(subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)) {
            try {
                ReviewPaper reviewPaper1 = new ReviewPaper();
                reviewPaper1.setSpecialistId(reviewPaper.getSpecialistId());
                reviewPaper1.setStatus(reviewPaper.getStatus());
                reviewPaper1.setComment(reviewPaper.getComment());
                reviewPaper1.setPaperId(reviewPaper.getPaperId());

                specialService.addReviewPaper1(reviewPaper1);
                ReturnResposeBody returnResposeBody = new ReturnResposeBody();
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(reviewPaper1);
                returnResposeBody.setJwtToken(token);
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e){
                return returnResposeBody1;
            }

        }
        return returnResposeBody1;
   }
}
