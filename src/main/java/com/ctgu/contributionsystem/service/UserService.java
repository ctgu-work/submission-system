package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:24
 * @ClassName UserService
 * @Version 1.0.0
 */
public interface UserService {
    User findByPhoneNumber(String phoneNumber);
    User findByUserId(Integer userId);
//    User modifyPassword(User user);
//    int modifyPasswordByUserId(@Param("password") String password, @Param("userId") Integer userId);
    User updateUser(User user);
    User addUser(User user);
    int countUserMoney(@Param("userId")Integer userId);

    void delete(Integer userId);

    User updateUserById(User user);

    List<User> findAll();

    Integer findByUserIdCount(@Param("userId") Integer userId);
}
