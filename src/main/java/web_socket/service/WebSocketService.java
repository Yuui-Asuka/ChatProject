package web_socket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import web_socket.controller.v5.User;
import web_socket.model.InMessage;
import web_socket.model.OutMessage;

import java.util.Map;

@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    public void sendTopicMessage(String dest, InMessage message) throws InterruptedException {
     for(int i = 0; i < 20; i++){
         template.convertAndSend(dest, new OutMessage(message.getContent()));
         Thread.sleep(500L);
     }
    }

    public void sendChatMessage(String dest, InMessage message) {
        template.convertAndSend(dest+message.getTo(), new OutMessage(
                message.getFrom() + " 发送了 " + message.getContent()
        ));
    }

    public void sendServerInfo() {
        long size = 1024 * 1024;
        int processors = Runtime.getRuntime().availableProcessors();
        Long freeMemory = Runtime.getRuntime().freeMemory() / size;
        Long maxMemory = Runtime.getRuntime().maxMemory() / size;
        String message = String.format("服务器可用处理器：%s, 总内存大小：%sMB, 可用内存大小：%sMB",
                processors, maxMemory, freeMemory);
        template.convertAndSend("/topic/server_info", new OutMessage(message));
    }
    //发送在线用户信息
    public void sendOnlineUser(Map<String, User> onlineUserMap) {
            String msg = "";
            for(Map.Entry<String, User> entry : onlineUserMap.entrySet()){
                msg = msg.concat(entry.getValue().getUserName()+"||");
            }
            System.out.println(msg);
            template.convertAndSend("/topic/onlineuser",new OutMessage(msg));
    }
    //发送聊天信息
    public void sendTopicChat(InMessage message) {
        String msg = message.getFrom() + "发送" + message.getContent();
        template.convertAndSend("/topic/chat", new OutMessage(msg));
    }
}
