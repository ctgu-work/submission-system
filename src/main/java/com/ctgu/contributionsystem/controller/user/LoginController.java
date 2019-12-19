package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.Md5Salt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    /**
     * @Author wh
     * @Description 登录
     * @Date 2019/12/19 17:59
     * @Param [phoneNumber, password]
     * @return java.lang.String
     **/
    @GetMapping("/login")
    @ResponseBody
    public String userLogin(@RequestParam("phoneNumber")String phoneNumber ,
                            @RequestParam("password")String password){
//        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(phoneNumber , password);
//        try {
//            subject.login(token);
//            return "index";
//        }
//        catch (AuthenticationException e){
//            return "login";
//        }
        User user = userService.findByPhoneNumber(phoneNumber);
        String token = JwtUtil.sign(phoneNumber,Md5Salt.Md5SaltCrypt(password));
        if(user.getPassword().equals(Md5Salt.Md5SaltCrypt(password))){
            return token;
        }
        return "login";

    }

    @GetMapping("/index")
    @ResponseBody
    public String index(){
        Subject subject = SecurityUtils.getSubject();
        if( subject.isAuthenticated() ){
            return "index";
        }
        else{
            return "login";
        }
    }
}
