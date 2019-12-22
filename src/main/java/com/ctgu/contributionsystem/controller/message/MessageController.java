package com.ctgu.contributionsystem.controller.message;

import com.ctgu.contributionsystem.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: contribution-system *
 * @classname: WebScoketController *
 * @author: lnback *
 * @create: 2019-12-20 21:44
 **/
@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;


    @PostMapping("/sendMessage")
    public String sendUserByUserId(
            @RequestParam("sender_id") Integer senderId,
            @RequestParam(required = false,value = "accept_ids[]") Integer[] acceptIds,
            @RequestParam("message") String message){

        /**
         * 1.接收者数组为空 ：群发消息，选择全部user
         */
        if(acceptIds.length == 0){
            if(messageService.sendMessageToAllUser(senderId,message)){
                return "true";
            }else {
                return "false";
            }
        }
        /**
         *2.接收者数组不为空：依次发送消息
         * 并计数检测是否全部发送
         */
        else {
            int cnt = 0;
            for(Integer acceptId : acceptIds){
                if(messageService.sendMessageToUserByUserId(senderId,acceptId,message)){
                    cnt ++;
                }
            }
            if(cnt != acceptIds.length){
                return "false";
            }else {
                return "true";
            }
        }
    }
    //TODO:接收消息


}
