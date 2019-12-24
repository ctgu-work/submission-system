package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.model.Paper;
import com.ctgu.contributionsystem.utils.WebSocket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;

/**
 * @program: contribution-system *
 * @classname: MessageServiceTest *
 * @author: lnback *
 * @create: 2019-12-21 14:04
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageServiceTest {

    @Autowired
    private PaperService paperService;

    @Test
    @Transactional
    public void test(){

        Paper paper = paperService.getPaper(5);
        paper.setTitle("linux");
        paper.setContent("linux大全");
        paper.setAuthor("大傻昆");
        paperService.updatePaper(paper);
    }
}
