package com.ctgu.contributionsystem.controller.admin;

import com.ctgu.contributionsystem.dto.ReturnResposeBody;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SuperAdminService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.Md5Salt;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/23
 * @description ：this is a code
 **/

@RestController
@RequestMapping("/admin")
public class SuperAdminController {

    @Autowired
    private SuperAdminService superAdminService;

    //所有管理
    @RequestMapping("/Superadmin/findall")
    public List<Admin> FindAll(HttpRequest request){
        try {
            List<Admin> list = superAdminService.findAll();
            return list;
        }catch (Exception e){
            return null;
        }
    }

    //添加管理
    @RequestMapping("/Superadmin/addAdmin")
    public ReturnResposeBody addUser(@RequestBody Admin admin, HttpRequest HttpServletRequest){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");

        ReturnResposeBody returnResposeBody2 = new ReturnResposeBody();
        returnResposeBody2.setMsg("您没有权限操作");
        returnResposeBody2.setStatus("200");
        try {
            int rank = superAdminService.findAdminBy(1);
            if(rank == 1) {
                Admin admin1 = new Admin();
                admin1.setNickName(admin.getNickName());
                admin1.setPhoneNumber(admin.getPhoneNumber());
                admin1.setRank(2);
                String Password2 = Md5Salt.Md5SaltCrypt(admin.getPassword());
                admin1.setPassword(Password2);
                System.out.println(admin1);
                superAdminService.addAdmin(admin1);
                ReturnResposeBody returnResposeBody3 = new ReturnResposeBody();
                returnResposeBody3.setMsg("success");
                returnResposeBody3.setStatus("200");
                return returnResposeBody3;
            }
            else{
                return returnResposeBody2;
            }
        }catch(Exception e){
            return returnResposeBody1;
        }
    }


    //添加管理
    @RequestMapping("/Superadmin/updateAdmin")
    public ReturnResposeBody updateUser(@RequestBody Admin admin, HttpRequest HttpServletRequest){
        ReturnResposeBody returnResposeBody1 = new ReturnResposeBody();
        returnResposeBody1.setMsg("error");
        returnResposeBody1.setStatus("200");

        ReturnResposeBody returnResposeBody2 = new ReturnResposeBody();
        returnResposeBody2.setMsg("您没有权限操作");
        returnResposeBody2.setStatus("200");
        try {
            int rank = superAdminService.findAdminBy(1);
            if(rank == 1) {
                Admin admin1 = new Admin();
                admin1.setAdminId(admin.getAdminId());
                admin1.setNickName(admin.getNickName());
                admin1.setRank(admin.getRank());
                admin1.setPhoneNumber(admin.getPhoneNumber());
                String Password2 = Md5Salt.Md5SaltCrypt(admin.getPassword());
                admin1.setPassword(Password2);
                superAdminService.updateAdmin(admin1);
                ReturnResposeBody returnResposeBody3 = new ReturnResposeBody();
                returnResposeBody3.setMsg("success");
                returnResposeBody3.setStatus("200");
                return returnResposeBody3;
            }
            else{
                return returnResposeBody2;
            }
        }catch(Exception e){
            return returnResposeBody1;
        }
    }

    //添加管理
    @RequestMapping("/Superadmin/deleteAdmin")
    public ReturnResposeBody deleteAdmin(HttpServletRequest request, @Param("adminId") Integer adminId){
        ReturnResposeBody returnResposeBody = new ReturnResposeBody();
        try{
            superAdminService.delete(adminId);
            returnResposeBody.setMsg("success");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        } catch (Exception e){
            returnResposeBody.setMsg("error");
            returnResposeBody.setStatus("200");
            return returnResposeBody;
        }
    }

}
