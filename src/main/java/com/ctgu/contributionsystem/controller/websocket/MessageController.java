package com.ctgu.contributionsystem.controller.websocket;

import com.ctgu.contributionsystem.service.MessageService;
import com.ctgu.contributionsystem.utils.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

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

    @GetMapping("/sendAllUser")
    public String sendAllUser(@RequestParam("message") String message){
        if(messageService.sendMessageToAllUser(message)){
            //可以修改返回值
            return "true";
        }
        return "false";
    }

    @GetMapping("/sendUserByUserId")
    public String sendUserByUserId(@RequestParam("user_id") Integer UserId,@RequestParam("message") String message){
//        webSocket.sendOneMessage();
        return "";
    }
}
