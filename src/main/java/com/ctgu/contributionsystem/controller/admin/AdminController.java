package com.ctgu.contributionsystem.controller.admin;
import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.dto.Vo;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.SpecialistStatus;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.AdminService;
import com.ctgu.contributionsystem.service.DtoService;
import com.ctgu.contributionsystem.service.VoService;
import com.ctgu.contributionsystem.utils.*;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/21
 * @description ：this is a code
 **/

@Controller
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private DtoService dtoService;

    @Autowired
    private VoService voService;

    @PostMapping("/login")
    @ResponseBody
    public ReturnResposeBody userLogin(@RequestParam("phoneNumber")String phoneNumber ,
                                       @RequestParam("password")String password){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");
        Admin admin = adminService.findByPhoneNumber(phoneNumber);
        String token1 = JwtUtil.sign(phoneNumber, Md5Salt.Md5SaltCrypt(password));
        if(admin.getPassword().equals(Md5Salt.Md5SaltCrypt(password))){
            redisUtils.set(phoneNumber ,token1,60 * 60);
            ReturnResposeBody returnResposeBody = new ReturnResposeBody();
            returnResposeBody.setMsg("success");
            returnResposeBody.setResult(admin);
            returnResposeBody.setJwtToken(token1);
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }

        return returnResposeBody1;

    }

    @GetMapping("/logout")
    @ResponseBody
    public String AdminLogout(HttpServletRequest request){
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        System.out.println(phoneNumber);
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)){
            //删除token
            System.out.println(phoneNumber);
            redisUtils.del(phoneNumber);
            return "1";
        }
        else{
            return "0";
        }
    }


    @GetMapping("/user/categorycout")
    @ResponseBody
    public List<SpecialCount> CategoryCount(HttpRequest request){

//        //从请求头中获取token
//        String token = request.getHeader("token");
//        //中介储存
//        Subject subject = SecurityUtils.getSubject();
//
//        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)) {
//
//        }
        List<SpecialCount> countn = dtoService.FindCount();
        return countn;
    }

    @GetMapping("/user/categoryo")
    @ResponseBody
    public List<Vo> CategoryCountVo(HttpRequest request){

//        //从请求头中获取token
//        String token = request.getHeader("token");
//        //中介储存
//        Subject subject = SecurityUtils.getSubject();
//
//        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)) {
//
//        }
        List<Vo> countVO = voService.FindCountVo();
        return countVO;
    }

    @GetMapping("/specialist")
    @ResponseBody
    public List<PageInfo> SpecialistFindAll(HttpServletRequest request, @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",name = "size") Integer size){

        try{
            List<Specialist> allSpecialistPage = adminService.findAll();
            JpaPageHelper jpaPageHelper = new JpaPageHelper();
            List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(allSpecialistPage,pageNum,size);
            return pageInfos;
        } catch (Exception e){
            return null;
        }
    }

    @GetMapping("/specialist/prohibit")
    @ResponseBody
    public String SpecialistStatus(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){

        try{
            System.out.println(specialistId);
            Integer s = adminService.updateSpecialitProhibit(specialistId);
            System.out.println(s);
            if(s != null) {
                return "1";
            }
            else{
                return "0";
            }
        }catch (Exception e){
            return "0";
        }

    }

    @GetMapping("/specialist/cancelprohibit")
    @ResponseBody
    public String SpecialistStatusCancel(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){

        try{
            System.out.println(specialistId);
            Integer s = adminService.updateSpecialitProhibit1(specialistId);
            System.out.println(s);
            if(s != null) {
                return "1";
            }
            else{
                return "0";
            }
        }catch (Exception e){
            return "0";
        }

    }

    @GetMapping("/specialist/category")
    @ResponseBody
    public String UpdateCategory(@RequestParam("specialist_Id") Integer specialistId,@RequestParam("category") Integer category){
        try{
            if (adminService.UpdateCategory(specialistId,category) != null) {
                return "1";
            }
            else{
                return "0";
            }
        }catch (Exception e){
            return "0";
        }
    }

    @GetMapping("/specialist/status")
    @ResponseBody
    public String UpdateStatus(@RequestParam("specialist_Id") Integer specialistId,@RequestParam("status") Integer status){
        try{
            if (adminService.Updatestatus(specialistId,status) != null) {
                return "1";
            }
            else{
                return "0";
            }
        }catch (Exception e){
            return "0";
        }
    }

    @GetMapping("/specialist/delete")
    @ResponseBody
    public String SpecialistDelete(@RequestParam("specialist_id") Integer specialistId){
        try{
            adminService.delete(specialistId);
            return "1";
        }catch (Exception e){
           return "0";
        }
    }
}
