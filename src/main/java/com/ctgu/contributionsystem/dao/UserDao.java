package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.User;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:23
 * @ClassName UserDao
 * @Version 1.0.0
 */
@Transactional
public interface UserDao extends JpaRepository<User, Integer>{
    User findByPhoneNumber(String PhoneNumber);
    User findByUserId(Integer userId);
//    @Modifying
    @Query(nativeQuery = true,value = "select money from user where user_id = :userId")
    int countUserMoney(@Param("userId")Integer userId);
}
