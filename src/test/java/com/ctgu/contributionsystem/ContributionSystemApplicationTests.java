package com.ctgu.contributionsystem;

import com.ctgu.contributionsystem.domain.TestRepository;
import com.ctgu.contributionsystem.entity.Demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContributionSystemApplicationTests {

    @Autowired
    TestRepository repository;

    @Test
    public void contextLoads() {
        List<Demo> list = repository.findAll();
        for (Demo demo : list) {
            System.out.println(demo);
        }
    }

}
