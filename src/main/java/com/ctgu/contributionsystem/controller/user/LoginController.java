package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.Md5Salt;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RedisUtils redisUtils;
    /**
     * @Author wh
     * @Description 登录
     * @Date 2019/12/19 17:59
     * @Param [phoneNumber, password]
     * @return java.lang.String
     **/
    @PostMapping("/login")
    @ResponseBody
    public ReturnResposeBody userLogin(@RequestParam("phoneNumber")String phoneNumber ,
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
            redisUtils.set(phoneNumber , token,60 * 60);
            ReturnResposeBody returnResposeBody = new ReturnResposeBody();
            returnResposeBody.setMsg("success");
            returnResposeBody.setResult(user);
            returnResposeBody.setJwtToken(token);
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;

    }

    /**
     * @Author wh
     * @Description 退出登录
     * @Date 2019/12/19 19:44
     * @Param [request]
     * @return java.lang.String
     **/
    @GetMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
//                String phoneNumber = JwtUtil.getPhoneNumber(token);
//                User user = userService.findByPhoneNumber(phoneNumber);
//                String newToken = JwtUtil.sign(phoneNumber,Md5Salt.Md5SaltCrypt(user.getPassword()));//退出登录刷新token
//                redisUtils.set("token" , newToken);
                redisUtils.del(phoneNumber);//退出登录删除token
                return "1";
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
