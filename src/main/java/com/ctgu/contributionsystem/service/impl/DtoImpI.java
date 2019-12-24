package com.ctgu.contributionsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

//@Service
//public class DtoImpI implements DtoService {
//
////    @Autowired
////    private DtoDao dtoDao;
////
////
////    @Override
////    public List<SpecialCount> FindCount() {
////        List<SpecialCount> list = new ArrayList<>();
////        List _list = dtoDao.findCount();
////        for (Object row : _list) {
////            Object[] cells = (Object[]) row;
////            SpecialCount orderGoods = new SpecialCount();
////            orderGoods.setUserId((int) cells[0]);
////            System.out.println(cells[0]);
////            orderGoods.setName((String) cells[1]);
////            orderGoods.setNickName((String) cells[2]);
////            orderGoods.setIdCard((String) cells[3]);
////            orderGoods.setPhoneNumber((String) cells[4]);
////            orderGoods.setPassWord((String) cells[5]);
////            orderGoods.setEmail((String) cells[6]);
////            String s = cells[7].toString();
////            orderGoods.setCount(Long.parseLong(s));
////            orderGoods.setMoney((BigInteger) cells[8]);
////            list.add(orderGoods);
////        }
////        return list;
////    }
//}
