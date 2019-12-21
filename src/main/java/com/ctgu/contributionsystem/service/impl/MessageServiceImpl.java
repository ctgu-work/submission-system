package com.ctgu.contributionsystem.service.impl;

import com.ctgu.contributionsystem.dao.MessageDao;
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
    public boolean sendMessageToAllUser(String message) {

        return false;
    }
}
