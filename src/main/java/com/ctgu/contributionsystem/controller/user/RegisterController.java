package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.EmailUtil;
import com.ctgu.contributionsystem.utils.Md5Salt;
import com.ctgu.contributionsystem.utils.RandomString;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;
    /**
     * @Author wh
     * @Description 用户注册
     * @Date 2019/12/20 18:53
     * @Param [user]
     * @return com.ctgu.contributionsystem.dto.ReturnResposeBody
     **/
    @PostMapping("/register")
    public ReturnResposeBody UserRegister(User user, @RequestParam("verify")String verify){
        String sendVerify = (String) redisUtils.get(user.getEmail());
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        if( !verify.equalsIgnoreCase(sendVerify) ){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
        String newPassword = Md5Salt.Md5SaltCrypt(user.getPassword());
        user.setPassword(newPassword);
        try {
            userService.addUser(user);
            returnResposeBody.setMsg("success");
            returnResposeBody.setResult(user);
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }

    @GetMapping("/register/getVerify")
    public String verityString(@RequestParam("email")String email){
//        String from = "1090230661@qq.com";
//        String Authorization = "kycksukluwltiggb";
        String from = "mr.djg@qq.com";
        String Authorization = "efyeivlwkaughaeg";
        String verify = RandomString.randStr(4);
        try{
            EmailUtil.qqEmail(from, email, Authorization,verify);
        }
        catch (Exception e){
            return "0";
        }
        //300秒
        try{
            redisUtils.set(email,verify,300);
            return "1";
        }
        catch (Exception e){
            return "0";
        }
    }


}
