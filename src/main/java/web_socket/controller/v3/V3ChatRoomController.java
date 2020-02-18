package web_socket.controller.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import web_socket.model.InMessage;
import web_socket.service.WebSocketService;

@Controller
public class V3ChatRoomController {

    @Autowired
    private WebSocketService ws;

    @MessageMapping("v3/single/chat")
    public void singleChat(InMessage message){
        ws.sendChatMessage("/chat/single/", message);
    }
}
