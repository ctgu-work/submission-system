package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.AdminDao;
import com.ctgu.contributionsystem.dao.SuperAdminDao;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.SuperAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/24
 * @description ：this is a code
 **/
@Service
public class SuperAdminServiceImpI implements SuperAdminService {

    @Autowired
    private SuperAdminDao superAdminDao;



    @Override
    public List<Admin> findAll(){
        return superAdminDao.findAll();
    }

    @Override
    public int findAdminBy(Integer adminId){
        return superAdminDao.findAdminById(adminId);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return superAdminDao.save(admin);
    }
}
