package com.ctgu.contributionsystem.controller.admin;
import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.AdminService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.service.VoService;
import com.ctgu.contributionsystem.utils.*;
import org.apache.http.HttpRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
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
    private VoService voService;

    @Autowired
    private UserService userService;

    //管理员毒登录
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

    //管理员登出
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

//    //统计申稿
//    @GetMapping("/user/categoryo")
//    @ResponseBody
//    public List<SpecialCount> CategoryCount(HttpRequest request){
//
////        //从请求头中获取token
////        String token = request.getHeader("token");
////        //中介储存
////        Subject subject = SecurityUtils.getSubject();
////
////        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)) {
////
////        }
////        List<SpecialCount> countn = dtoService.FindCount();
////        return countn;
//    }
    //统计投稿数
    @GetMapping("/user/categorcout")
    @ResponseBody
    public List<SpecialCount> CategoryCountVo(HttpRequest request){

//        //从请求头中获取token
//        String token = request.getHeader("token");
//        //中介储存
//        Subject subject = SecurityUtils.getSubject();
//
//        if(subject.isAuthenticated() && redisUtils.get("token").equals(token)) {
//
//        }
        List<User> user  = userService.findAll();
        List<SpecialCount> list = new LinkedList<>();
        for(User u : user){
            Integer in = userService.findByUserIdCount(u.getUserId());
            SpecialCount specialCount = new SpecialCount();
            specialCount.setUserId(u.getUserId());
            specialCount.setMoney(u.getMoney());
            specialCount.setEmail(u.getEmail());
            specialCount.setPassWord(u.getPassword());
            specialCount.setPhoneNumber(u.getPhoneNumber());
            specialCount.setIdCard(u.getIdCard());
            specialCount.setName(u.getName());
            specialCount.setCount(in);
            list.add(specialCount);
        }

        return list;
    }

    //专家审稿列表
    @GetMapping("/specialist")
    @ResponseBody
    public List<Specialist> SpecialistFindAll(HttpServletRequest request, @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum, @RequestParam(defaultValue = "1",name = "size") Integer size){

        try{
            List<Specialist> allSpecialistPage = adminService.findAllByStatus();
//            JpaPageHelper jpaPageHelper = new JpaPageHelper();
//            List<PageInfo> pageInfos = jpaPageHelper.SetStartPage(allSpecialistPage,pageNum,size);
            return allSpecialistPage;
        } catch (Exception e){
            return null;
        }
    }

    //查看某个专家
    @GetMapping("/specialist/findSpecialit")
    @ResponseBody
    public ReturnResposeBody FindSpecialist(@RequestBody Specialist specialist){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            Specialist specialist1 = adminService.findSpecialistById(specialist.getSpecialistId());
            returnResposeBody.setMsg("success");
            returnResposeBody.setResult(specialist1);
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }catch (Exception e){
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
    }
    //专家禁用
    @GetMapping("/specialist/prohibit")
    @ResponseBody
    public ReturnResposeBody SpecialistStatus(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            Integer s = adminService.updateSpecialitProhibit(specialistId);
            if(s != null) {
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
            else{
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }catch (Exception e){
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }

    }

    //专家取消禁用
    @GetMapping("/specialist/cancelprohibit")
    @ResponseBody
    public ReturnResposeBody SpecialistStatusCancel(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            Integer s = adminService.updateSpecialitProhibit1(specialistId);
            System.out.println(s);
            if(s != null) {
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
            else{
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }catch (Exception e){
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }

    }

    //专家修改分类
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

    //修改专家状态
    @GetMapping("/specialist/status")
    @ResponseBody
    public ReturnResposeBody UpdateStatus(@RequestParam("specialist_Id") Integer specialistId,@RequestParam("status") Integer status){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            if (adminService.Updatestatus(specialistId,status) != null) {
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
            else{
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }catch (Exception e){
            returnResposeBody.setMsg("error");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
    }

    //专家删除
    @GetMapping("/specialist/delete")
    @ResponseBody
    public ReturnResposeBody SpecialistDelete(@RequestParam("specialist_id") Integer specialistId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            adminService.delete(specialistId);
            returnResposeBody.setMsg("success");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }catch (Exception e){
            returnResposeBody.setMsg("error");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
    }
}
