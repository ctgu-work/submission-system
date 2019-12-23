package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.VoDao;
import com.ctgu.contributionsystem.dto.SpecialCount;
import com.ctgu.contributionsystem.dto.Vo;
import com.ctgu.contributionsystem.service.VoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : kun
 * @date ： 2019/12/22
 * @description ：this is a code
 **/
@Service
public class VoServiceImpI implements VoService {

    @Autowired
    private VoDao voDao;

    @Override
    public List<Vo> FindCountVo() {
        List<Vo> list = new ArrayList<>();
        List _list = voDao.findCountVo();
        for (Object row : _list) {
            Object[] cells = (Object[]) row;
            Vo vo = new Vo();
            vo.setSpecialistId((int) cells[0]);
            System.out.println(cells[0]);
            vo.setName((String) cells[1]);
            System.out.println(cells[1]);
            String s = cells[2].toString();
            vo.setCount(Long.parseLong(s));
            list.add(vo);
        }
        return list;
    }
}
