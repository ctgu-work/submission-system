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
    boolean sendMessageToAllUser(Integer senderId,String message);

    /**
     * 发送消息给选中用户
     * @param senderId
     * @param acceptId
     * @param message
     * @return
     */
    boolean sendMessageToUserByUserId(Integer senderId, Integer acceptId, String message);


    Integer countAllMessage();
}
