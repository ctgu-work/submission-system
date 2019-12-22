package com.ctgu.contributionsystem.service;

import com.ctgu.contributionsystem.utils.WebSocket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    MessageService messageService;

    @Test
    public void testSendMessage(){
        messageService.sendMessageToAllUser(1,"hhh");
    }
}
