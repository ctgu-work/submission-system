package com.ctgu.contributionsystem.controller.admin;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SuperAdminService;
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
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/23
 * @description ：this is a code
 **/

@RestController
@RequestMapping("/admin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    @Autowired
    private RedisUtils redisUtils;

    //所有管理
    @RequestMapping("/Superadmin/findall")
    public ReturnResposeBody FindAll(HttpServletRequest request){

        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                List<Admin> list = superAdminService.findAll();
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                returnResposeBody.setResult(list);
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

    //添加管理
    @RequestMapping("/Superadmin/addAdmin")
    public ReturnResposeBody addUser(@RequestBody Admin admin, HttpServletRequest request){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");

        ReturnResposeBody returnResposeBody2 = new ReturnResposeBody();
        returnResposeBody2.setMsg("您没有权限操作");
        returnResposeBody2.setStatus("200");


        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                int rank = superAdminService.findAdminBy(1);
                if (rank == 1) {
                    Admin admin1 = new Admin();
                    admin1.setNickName(admin.getNickName());
                    admin1.setPhoneNumber(admin.getPhoneNumber());
                    admin1.setRank(2);
                    String Password2 = Md5Salt.Md5SaltCrypt(admin.getPassword());
                    admin1.setPassword(Password2);
                    System.out.println(admin1);
                    superAdminService.addAdmin(admin1);
                    ReturnResposeBody returnResposeBody3 = new ReturnResposeBody();
                    returnResposeBody3.setMsg("success");
                    returnResposeBody3.setStatus("200");
                    return returnResposeBody3;
                } else {
                    return returnResposeBody2;
                }
            } catch (Exception e) {
                return returnResposeBody1;
            }
        }
        return returnResposeBody1;
    }


    //添加管理
    @RequestMapping("/Superadmin/updateAdmin")
    public ReturnResposeBody updateUser(@RequestBody Admin admin, HttpServletRequest request){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");

        ReturnResposeBody returnResposeBody2 = new ReturnResposeBody();
        returnResposeBody2.setMsg("您没有权限操作");
        returnResposeBody2.setStatus("200");

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                int rank = superAdminService.findAdminBy(1);
                if (rank == 1) {
                    Admin admin1 = new Admin();
                    admin1.setAdminId(admin.getAdminId());
                    admin1.setNickName(admin.getNickName());
                    admin1.setRank(admin.getRank());
                    admin1.setPhoneNumber(admin.getPhoneNumber());
                    String Password2 = Md5Salt.Md5SaltCrypt(admin.getPassword());
                    admin1.setPassword(Password2);
                    superAdminService.updateAdmin(admin1);
                    ReturnResposeBody returnResposeBody3 = new ReturnResposeBody();
                    returnResposeBody3.setMsg("success");
                    returnResposeBody3.setStatus("200");
                    return returnResposeBody3;
                } else {
                    return returnResposeBody2;
                }
            } catch (Exception e) {
                return returnResposeBody1;
            }
        }
        return returnResposeBody1;
    }

    //添加管理
    @RequestMapping("/Superadmin/deleteAdmin")
    public ReturnResposeBody deleteAdmin(HttpServletRequest request, @Param("adminId") Integer adminId) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                int rank = superAdminService.findAdminBy(1);
                if (rank == 1) {
                    superAdminService.delete(adminId);
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    returnResposeBody.setMsg("error");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                }
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

}
