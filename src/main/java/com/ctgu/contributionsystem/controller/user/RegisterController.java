package com.ctgu.contributionsystem.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-19 19:31
 * @ClassName RegisterController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class RegisterController {


    @PostMapping("/register")
    public String UserRegister(){
        return "true";
    }


}
