package com.guojr.controller;

import com.guojr.domain.WiselyMessage;
import com.guojr.domain.WiselyResponse;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WsController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/getResponse")
    public WiselyResponse send(WiselyMessage message) throws Exception{
        Thread.sleep(3000);
        return new WiselyResponse("welcome:"+message.getName());
    }
}
