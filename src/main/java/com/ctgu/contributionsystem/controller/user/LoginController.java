package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:18
 * @ClassName LoginController
 * @Version 1.0.0
 */
@Controller
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public String userLogin(@RequestParam("phoneNumber")String phoneNumber ,
                            @RequestParam("password")String password){
        return "index";
    }

}
