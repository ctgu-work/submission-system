package com.ctgu.contributionsystem.service;

/**
 * @program: contribution-system *
 * @classname: MessageService *
 * @author: lnback *
 * @create: 2019-12-21 12:22
 **/

public interface MessageService {
    /**
     * 发送消息给所有用户
     * @param message
     * @return
     */
    boolean sendMessageToAllUser(String message);
}
