package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.MessageDao;
import com.ctgu.contributionsystem.model.Message;
import com.ctgu.contributionsystem.service.MessageService;
import com.ctgu.contributionsystem.utils.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: contribution-system *
 * @classname: MessageServiceImpl *
 * @author: lnback *
 * @create: 2019-12-21 12:30
 **/
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private WebSocket webSocket;


    @Override
    public boolean sendMessageToAllUser(Integer senderId, String message) {
        Message m = new Message();
        m.setSenderId(senderId);
        m.setContent(message);
        m.setAcceptId(0);
        if(webSocket.sendAllMessage(message)){
            m.setStatus(1);
            messageDao.save(m);
            return true;
        }else {
            m.setStatus(2);
            messageDao.save(m);
            return false;
        }
    }

    @Override
    public boolean sendMessageToUserByUserId(Integer senderId, Integer acceptId, String message) {
        Message m = new Message();
        m.setSenderId(senderId);
        m.setContent(message);
        m.setAcceptId(acceptId);
        if(webSocket.sendOneMessage(senderId,message)){
            m.setStatus(1);
            messageDao.save(m);
            return true;
        }else {
            m.setStatus(2);
            messageDao.save(m);
            return false;
        }
    }

    @Override
    public Integer countAllMessage() {
        return (int) messageDao.count();
    }
}
