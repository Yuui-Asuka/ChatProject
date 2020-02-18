//package web_socket.controller.v1;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.handler.annotation.SendTo;
//import web_socket.model.InMessage;
//import web_socket.model.OutMessage;
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.stereotype.Controller;
//import web_socket.service.WebSocketService;
//
//@Controller
//public class GameInfoController {
//
//    @Autowired
//    private WebSocketService ws;
//
//    @MessageMapping("/v1/chat")
//    @SendTo("/topic/game_chat")
//    public OutMessage gameInfo(InMessage message) throws InterruptedException {
//        return new OutMessage(message.getContent());
//       // ws.sendMessageTopic("/topic/game_info", message);
//    }
//}
