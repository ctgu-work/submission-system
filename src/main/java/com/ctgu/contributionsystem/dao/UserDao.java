package com.ctgu.contributionsystem.dao;

import com.ctgu.contributionsystem.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
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
}
