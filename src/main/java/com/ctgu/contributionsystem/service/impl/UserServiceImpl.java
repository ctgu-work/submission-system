package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.UserDao;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 20:27
 * @ClassName UserServiceImpl
 * @Version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userDao.findByPhoneNumber(phoneNumber);
    }

    @Override
    public User findByUserId(Integer userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public User updateUser(User user) {
        return userDao.saveAndFlush(user);
    }


    @Override
    public User addUser(User user) {
        return userDao.save(user);
    }

    @Override
    public int countUserMoney(Integer userId) {
        return userDao.countUserMoney(userId);
    }


//    @Override
//    public User modifyPassword(User user) {
//        return userDao.saveAndFlush(user);
//    }

    @Override
    public void delete(Integer userId){
        userDao.deleteById(userId);
    }

    @Override
    public User updateUserById(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> findAll(){
        return userDao.findAll();
    }
}
