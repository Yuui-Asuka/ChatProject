package web_socket.controller.v4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import web_socket.service.WebSocketService;

@Controller
public class ServerInfoController {

    @Autowired
    private WebSocketService ws;

    @MessageMapping("/v4/schedule/push")
    @Scheduled(fixedRate = 3000)
    public void sendServiceInfo(){
        ws.sendServerInfo();
    }
}
