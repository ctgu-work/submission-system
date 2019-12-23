package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.DtoDao;
import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.service.DtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/20
 * @description ：this is a code
 **/

@Service
public class DtoImpI implements DtoService {

    @Autowired
    private DtoDao dtoDao;


    @Override
    public List<SpecialCount> FindCount() {
        List<SpecialCount> list = new ArrayList<>();
        List _list = dtoDao.findCount();
        for (Object row : _list) {
            Object[] cells = (Object[]) row;
            SpecialCount orderGoods = new SpecialCount();
            orderGoods.setUserId((int) cells[0]);
            System.out.println(cells[0]);
            orderGoods.setName((String) cells[1]);
            System.out.println(cells[1]);
            String s = cells[2].toString();
            orderGoods.setCount(Long.parseLong(s));
            list.add(orderGoods);
        }
        return list;
    }
}
