package com.ctgu.contributionsystem.controller.user;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-19 20:10
 * @ClassName UpdateController
 * @Version 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UpdateController {
    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserService userService;

    /**
     * @Author wh
     * @Description 更新信息
     * @Date 2019/12/19 21:55
     * @Param [multipartFile, avatarName, request]
     * @return java.lang.String
     **/
    @PostMapping("/profile")
    public ReturnResposeBody updateUser(@RequestParam(value = "avatar",required = false)MultipartFile multipartFile ,
                                  User user ,HttpServletRequest request ){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
                if( multipartFile != null ){
                    String avatarName = multipartFile.getName();
                    String extension = UploadUtil.getFileExtension(multipartFile);
                    //判断是否是图片
                    if( extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".png")
                    || extension.equalsIgnoreCase(".jpeg")){
                        String avatarUrl = Oss.upLoadFile(avatarName,multipartFile);
                        System.out.println(avatarUrl);
                        if( avatarUrl.equals("error") ) {
                            returnResposeBody.setMsg("error");
                            return returnResposeBody;
                        }
                        else user.setAvatarUrl(avatarUrl);
                    }
                    else{
                        returnResposeBody.setMsg("error");
                        return returnResposeBody;
                    }
                }
                User user1 = userService.findByPhoneNumber(user.getPhoneNumber());
                User updateUser = userService.updateUser(user1);
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(updateUser);
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
            else{
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        catch (Exception e){
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }

    }

    /**
     * @Author wh
     * @Description 修改密码
     * @Date 2019/12/19 21:55
     * @Param [newPassword, request]
     * @return java.lang.String
     **/
    @PostMapping("/profile/pass")
    public String updatePassword(@RequestParam("newPassword")String newPassword , HttpServletRequest request){
        try{
            String token = request.getHeader("token");//从请求头中获取token
            Subject subject = SecurityUtils.getSubject();
            String phoneNumber = JwtUtil.getPhoneNumber(token);
            if( subject.isAuthenticated() && redisUtils.get(phoneNumber).equals(token)){
                User user = userService.findByPhoneNumber(phoneNumber);
                String cryPassword = Md5Salt.Md5SaltCrypt(newPassword);
                String newToken = JwtUtil.sign(phoneNumber, cryPassword);
                user.setPassword(cryPassword);
                userService.updateUser(user);
                redisUtils.del("token");//退出登录删除token
                redisUtils.set("token" , newToken);
                return newToken;
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
