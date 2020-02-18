package web_socket.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import java.util.Objects;

@Component
public class ConnectEventListener implements ApplicationListener<SessionConnectEvent> {
    public void onApplicationEvent(SessionConnectEvent event){
        StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(event.getMessage());
        System.out.println("[ConnectEventListener]监听器事件 类型" + stompHeaderAccessor.getCommand().getMessageType());
    }

}
