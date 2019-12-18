package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.User;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:24
 * @ClassName UserService
 * @Version 1.0.0
 */
public interface UserService {
    User findByPhoneNumber(String phoneNumber);
}
