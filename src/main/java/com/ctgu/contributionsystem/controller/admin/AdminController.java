package com.ctgu.contributionsystem.controller.admin;
import com.ctgu.contributionsystem.dao.SpecialistDao;
import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.Specialist;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.dto.Vo;
import com.ctgu.contributionsystem.model.*;
import com.ctgu.contributionsystem.service.*;
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
    private UserService userService;
    @Autowired
    private PaperService paperService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private SpecialService specialService;

    @Autowired
    private SpecialistCategoryService specialistCategoryService;

    @Autowired
    private ReviewPaperService reviewPaperService;

    //管理员登录
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
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)){
            //删除token
            redisUtils.del(phoneNumber);
            return "1";
        }
        else{
            return "0";
        }
    }

    //专家管理 获取所有分类
    @GetMapping("/get/category")
    @ResponseBody
    public ReturnResposeBody GetCategory(HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)){
            try{
                List<SpecialistCategory> list =  specialistCategoryService.findAll();
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(list);
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }catch(Exception e){
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setMsg("error");
        returnResposeBody.setStatus("200");
        return returnResposeBody;
    }

    //专家管理 统计审稿数表格
    @GetMapping("/user/categoryo")
    @ResponseBody
    public ReturnResposeBody CategoryCount(HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)){
        try {
            List<Specialist> specialist = specialService.findAll();
            List<Vo> list = new LinkedList<>();
            for (Specialist s : specialist) {
                Integer in = reviewPaperService.findBySpecailistIdCount(s.getSpecialistId());
                User user = userService.findByUserId(s.getUserId());
                Vo vo = new Vo();
                vo.setUserId(user.getUserId());
                vo.setStatus(s.getStatus());
                vo.setPhoneNumber(user.getPhoneNumber());
                vo.setCategory(s.getCategory());
                vo.setName(user.getName());
                vo.setSpecialistId(s.getSpecialistId());
                vo.setCount(in);
                list.add(vo);
            }
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("success");
            returnResposeBody.setResult(list);
            return returnResposeBody;
        }catch (Exception e){
            returnResposeBody.setStatus("200");
            returnResposeBody.setMsg("error");
            return returnResposeBody;
        }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;

    }

    //用户管理 统计投稿表
    @GetMapping("/user/categorcout")
    @ResponseBody
    public ReturnResposeBody CategoryCountVo(HttpServletRequest request){

        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {
            try {
                List<User> user = userService.findAll();
                List<SpecialCount> list = new LinkedList<>();
                for (User u : user) {
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
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(list);
                return returnResposeBody;
            }catch (Exception e){
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家审核列表
    @GetMapping("/specialist")
    @ResponseBody
    public ReturnResposeBody SpecialistFindAll(HttpServletRequest request,
                                               @RequestParam(defaultValue = "1",name = "pageNum") Integer pageNum,
                                               @RequestParam(defaultValue = "1",name = "size") Integer size){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {
            try {
                List<Specialist> allSpecialistPage = adminService.findAllByStatus();
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(allSpecialistPage);
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家审核 查看某个专家
    @GetMapping("/specialist/findSpecialit")
    @ResponseBody
    public ReturnResposeBody FindSpecialist(@RequestParam("specialistId") Integer specialistId,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {
            try {
                Specialist specialist1 = adminService.findSpecialistById(specialistId);
                User user = userService.findByUserId(specialist1.getUserId());
                Vo vo = new Vo();
                vo.setSpecialistId(specialist1.getSpecialistId());
                vo.setName(user.getName());
                vo.setCategory(specialist1.getCategory());
                vo.setUserId(specialist1.getUserId());
                vo.setPhoneNumber(user.getPhoneNumber());
                vo.setStatus(specialist1.getStatus());
                returnResposeBody.setMsg("success");
                returnResposeBody.setResult(vo);
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家审核 修改状态
    @GetMapping("/specialist/updatestatus")
    @ResponseBody
    public ReturnResposeBody UpdateStatus(HttpServletRequest request,@RequestParam("specialistId") Integer specialistId,
                                          @RequestParam("status") Integer status){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {
            try {
                Integer s = adminService.Updatestatus(specialistId,status);
                if (s != null) {
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    returnResposeBody.setStatus("200");
                    returnResposeBody.setMsg("error");
                    return returnResposeBody;
                }
            } catch (Exception e) {
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家管理 专家禁用
    @GetMapping("/specialist/prohibit")
    @ResponseBody
    public ReturnResposeBody SpecialistStatus(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {
            try {
                Integer s = adminService.updateSpecialitProhibit(specialistId);
                if (s != null) {
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    returnResposeBody.setStatus("200");
                    returnResposeBody.setMsg("error");
                    return returnResposeBody;
                }
            } catch (Exception e) {
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家管理 取消禁用
    @GetMapping("/specialist/cancelprohibit")
    @ResponseBody
    public ReturnResposeBody SpecialistStatusCancel(HttpServletRequest request,@RequestParam("specialist_id") Integer specialistId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber).equals(token1)) {

            try {
                Integer s = adminService.updateSpecialitProhibit1(specialistId);
                System.out.println(s);
                if (s != null) {
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    returnResposeBody.setStatus("200");
                    returnResposeBody.setMsg("error");
                    return returnResposeBody;
                }
            } catch (Exception e) {
                returnResposeBody.setStatus("200");
                returnResposeBody.setMsg("error");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    //专家管理 专家修改
    @GetMapping("/specialist/update")
    @ResponseBody
    public ReturnResposeBody UpdateSpecailist(@RequestParam("specialist_Id") Integer specialistId,@RequestParam("category") Integer category,
                                              @RequestParam("status") Integer status,@RequestParam("name") String name,
                                 @RequestParam("nickName") String nickName,@RequestParam("phoneNumber") String phoneNumber,HttpServletRequest request){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if(subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                adminService.UpdateCategory(specialistId, category);
                adminService.Updatestatus(specialistId, status);
                Specialist specialist = adminService.findSpecialistById(specialistId);
                User user = userService.findByUserId(specialist.getUserId());
                Integer i = adminService.UpdateUser(user.getUserId(), name, nickName, phoneNumber);
                if (i != null) {
                    returnResposeBody.setMsg("success");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                } else {
                    returnResposeBody.setMsg("error");
                    returnResposeBody.setStatus("200");
                    return returnResposeBody;
                }
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }


    //专家管理 专家删除
    @GetMapping("/specialist/delete")
    @ResponseBody
    public ReturnResposeBody SpecialistDelete(@RequestParam("specialist_id") Integer specialistId,HttpServletRequest request) {
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();

        //从请求头中获取token
        String token1 = request.getHeader("token");
        //中介储存
        Subject subject1 = SecurityUtils.getSubject();
        String phoneNumber1 = JwtUtil.getPhoneNumber(token1);
        //shiro登录判断
        if (subject1.isAuthenticated() && redisUtils.get(phoneNumber1).equals(token1)) {
            try {
                adminService.delete(specialistId);
                returnResposeBody.setMsg("success");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            } catch (Exception e) {
                returnResposeBody.setMsg("error");
                returnResposeBody.setStatus("200");
                return returnResposeBody;
            }
        }
        returnResposeBody.setStatus("200");
        returnResposeBody.setMsg("error");
        return returnResposeBody;
    }

    @GetMapping("/paper/countAllPaper")
    @ResponseBody
    public Integer countAllPaper(){
        try {
            return paperService.countAllPaper();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/message/countAllMessage")
    @ResponseBody
    public Integer countAllMessage(){
        try {
            return messageService.countAllMessage();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
