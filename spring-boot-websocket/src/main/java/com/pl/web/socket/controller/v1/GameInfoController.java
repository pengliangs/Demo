package com.pl.web.socket.controller.v1;

import com.pl.web.socket.model.InMessage;
import com.pl.web.socket.model.OutMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * author pengliang  2018-05-27 14:52
 */

@Controller
public class GameInfoController {



    @MessageMapping("/gameInfo")
    @SendTo("/topic/game")
    public OutMessage gameInfo(InMessage inMessage){
        System.out.println("v1.gameInfo");
        return new OutMessage(inMessage.getFrom(),inMessage.getContent());
    }


}
