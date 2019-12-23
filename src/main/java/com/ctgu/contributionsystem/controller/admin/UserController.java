package com.ctgu.contributionsystem.controller.admin;

import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.Md5Salt;
import org.apache.http.HttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@RestController
@RequestMapping("/admin")
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping("/addUser")
    public String addUser(@RequestBody User user, HttpRequest request){
        try {
            User user1 = new User();
            user1.setUserId(user.getUserId());
            user1.setName(user.getName());
            user1.setNickName(user.getNickName());
            user1.setIdCard(user.getIdCard());
            user1.setSex(user.getSex());
            user1.setAge(user.getAge());
            user1.setPhoneNumber(user.getPhoneNumber());
            String Password1 = Md5Salt.Md5SaltCrypt(user.getPassword());
            user1.setPassword(Password1);
            user1.setEmail(user.getEmail());
            user1.setAvatarUrl(user.getAvatarUrl());
            user1.setDescription(user.getDescription());
            user1.setMoney(user.getMoney());
            userService.addUser(user1);
            return "1";
        }catch(Exception e){
           return "0";
        }
    }

    @RequestMapping("/updateUser")
    public String updateUser(@RequestBody User user ,HttpRequest request){
        try{
            userService.updateUserById(user);
            return "1";
        } catch (Exception e){
            return "0";
        }
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@Param("userId") Integer userId){
        try{
            userService.delete(userId);
            return "1";
        } catch (Exception e){
            return "0";
        }

    }
}
