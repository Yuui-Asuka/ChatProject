package web_socket.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import web_socket.model.InMessage;
import web_socket.service.WebSocketService;

@Controller
public class V2GameRankController {

    @Autowired
    private WebSocketService ws;

    @MessageMapping("/v2/chat")
    //当很多人订阅的时候，不能通用，固定发送给指定订阅者
    //@SendTo("/topic/game_chat")
    public void gameRank(InMessage message) throws InterruptedException {
        //  return new OutMessage(message.getContent());
        ws.sendTopicMessage("/topic/game_rank", message);
    }
}