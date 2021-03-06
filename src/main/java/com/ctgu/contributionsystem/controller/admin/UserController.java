package com.ctgu.contributionsystem.controller.admin;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.Md5Salt;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@RestController
@RequestMapping("/admin")
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;


    //添加用户
    @RequestMapping("/addUser")
    public ReturnResposeBody addUser(@RequestBody User user, HttpServletRequest request) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                User user1 = new User();
                user1.setName(user.getName());
                user1.setNickName(user.getNickName());
                user1.setIdCard(user.getIdCard());
                user1.setSex(user.getSex());
                user1.setAge(user.getAge());
                user1.setPhoneNumber(user.getPhoneNumber());
                String Password1 = Md5Salt.Md5SaltCrypt(user.getPassword());
                user1.setPassword(Password1);
                user1.setEmail(user.getEmail());
                user1.setAvatarUrl(user.getAvatarUrl());
                user1.setDescription(user.getDescription());
                user1.setMoney(user.getMoney());
                userService.addUser(user1);
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }

    //更新用户
    @RequestMapping("/updateUser")
    public ReturnResposeBody updateUser(@RequestBody User user ,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                userService.updateUserById(user);
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }

    //删除用户
    @RequestMapping("/deleteUser")
    public ReturnResposeBody deleteUser(@Param("userId") Integer userId,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                userService.delete(userId);
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }

    @ResponseBody
    @RequestMapping("/countAllUser")
    public Integer countAllUser(){
        try {
            return userService.countAllUser();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
