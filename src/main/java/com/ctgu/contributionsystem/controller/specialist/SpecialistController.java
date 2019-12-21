package com.ctgu.contributionsystem.controller.specialist;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SpecialService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import com.ctgu.contributionsystem.utils.Md5Salt;
import com.ctgu.contributionsystem.utils.RedisUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@Controller
@RequestMapping("/specialist")
public class SpecialistController {

    @Autowired
    private SpecialService specialService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtils redisUtils;


    @PostMapping("/login")
    @ResponseBody
    public ReturnResposeBody SpecialistLogin(@RequestParam("phoneNumber")String phoneNumber ,
                            @RequestParam("password")String password){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");
        User user = userService.findByPhoneNumber(phoneNumber);
        System.out.println(user);
        String token = JwtUtil.sign(phoneNumber, Md5Salt.Md5SaltCrypt(password));
        if(user.getPassword().equals(Md5Salt.Md5SaltCrypt(password))){
            try {
                Specialist specialist = specialService.findByUserId(user.getUserId());
                if (specialist.getStatus() == 2) {
                    redisUtils.set("token", token);
                    ReturnResposeBody returnResposeBody = new ReturnResposeBody();
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setResult(specialist);
                    returnResposeBody.setJwtToken(token);
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    return returnResposeBody1;
                }
            }catch (Exception e) {
                return returnResposeBody1;
            }
        }
        return returnResposeBody1;

    }

    @GetMapping("/logout")
    @ResponseBody
    public String SpecialistLogout(HttpServletRequest request){
            //从请求头中获取token
            String token = request.getHeader("token");
            //中介储存
            Subject subject = SecurityUtils.getSubject();
            //shiro登录判断
            if( subject.isAuthenticated() && redisUtils.get("token").equals(token)){
                //删除token
                redisUtils.del("token");
                return "1";
            }
            else{
                return "0";
            }
        }

    @GetMapping("/paperlist")
    @ResponseBody
    public List<Paper> SpecialistFindAll(HttpServletRequest request, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",value = "size") Integer size){
        //从请求头中获取token
        String token = request.getHeader("token");
        //中介储存
        Subject subject = SecurityUtils.getSubject();
        //shiro登录判断
//        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)){
//        System.out.println(pageNum);
//        System.out.println(size);
//        PageHelper.startPage(pageNum,size);
//        List<Paper> list = specialService.findAll();
//        PageInfo<Paper> pageInfo = new PageInfo<Paper>(list);
//        System.out.println(pageInfo.getPageNum());
        Pageable pageable = PageRequest.of(pageNum, size);
        Page<Paper> allPicturesPage = specialService.findAll(pageable);
        List<Paper> allPictures = allPicturesPage.getContent();
//            List<Paper> list = specialService.findAll();
            return allPictures;
//        }
//        return null;
    }

}
