package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/")
    public User refresh(HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                String phoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(phoneNumber);
                return user;
            }
            else{
                return null;
            }
        }
        catch (Exception e){
            return null;
        }
    }

    //个人主页
    @GetMapping("/index")
    public String index(HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                String phoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(phoneNumber);
                Integer userId = user.getUserId();
            }
//                redisUtils.del("token");//退出登录删除token
//
            else{
                return "0";
            }
        }
        catch (Exception e){
            return "0";
        }
        return "1";
    }

    @GetMapping("/show/{userId}")
    public String articleList(@PathVariable Integer userId){
        return "1";
    }


//    查看稿件状态
    @GetMapping("/profile/article")
    public String showArticleStatus(HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                String phoneNumber = JwtUtil.getPhoneNumber(token);
                User user = userService.findByPhoneNumber(phoneNumber);
                Integer userId = user.getUserId();
                return "0";
            }
            else{
                return "0";
            }
        }
        catch (Exception e){
            return "0";
        }
    }
}
