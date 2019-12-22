package com.ctgu.contributionsystem.controller.specialist;

import com.ctgu.contributionsystem.service.AdminService;
import com.ctgu.contributionsystem.service.SpecialService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : kun
 * @date ： 2019/12/21
 * @description ：this is a code
 **/

@Controller
@RequestMapping("/admin")
public class AdminController {

//
//    @Autowired
//    private AdminService adminService;
//
//
//    @GetMapping("/user/categorycout")
//    @ResponseBody
//    public Integer CategoryCount(HttpRequest request){

//        //从请求头中获取token
//        String token = request.getHeader("token");
//        //中介储存
//        Subject subject = SecurityUtils.getSubject();
//
//        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)) {
//
//        }
//        Integer countn = adminService.FindCount();
//        return countn;
//    }


}
