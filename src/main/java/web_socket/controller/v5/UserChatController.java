package web_socket.controller.v5;

import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web_socket.model.InMessage;
import web_socket.service.WebSocketService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Controller
public class UserChatController {

    @Autowired
    private WebSocketService ws;

    public static Map<String, String> userMap = new HashMap<String, String>();

    static {
        userMap.put("Jack", "123");
        userMap.put("Mary", "456");
        userMap.put("Kit", "789");
        userMap.put("Keen", "000");
        userMap.put("Jim", "q1w2e3r4t5y6");
    }

    public static Map<String, User> onlineUserMap = new HashMap<>();

    static {
        onlineUserMap.put("123", new User("admin", "888"));
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String userLogin(@RequestParam(value = "username", required = true) String username,
                            @RequestParam(value = "pwd", required = true) String pwd,
                            HttpSession session) {
        String password = userMap.get(username);
        if (pwd.equals(password)) {

            User user = new User(username, pwd);
            String sessionId =  session.getId();
            //登录成功
            onlineUserMap.put(sessionId, user);
            //页面跳转重定向
            return "/v5/chat.html";
        }else {
            return "error";
        }
    }

    @Scheduled(fixedRate = 200000000)
    public void onlineUser(){
        ws.sendOnlineUser(onlineUserMap);
    }

    @MessageMapping("/v5/chat")
    public void topicChat(InMessage message, SimpMessageHeaderAccessor headerAccessor){
        String sessionId  = headerAccessor.getSubscriptionId();
      //  String sessionId2 = headerAccessor.getId().toString();
        String sessionId3 = headerAccessor.getSessionId();
        Map<String, Object> session = headerAccessor.getSessionAttributes();
        Principal sessionId4 = headerAccessor.getUser();
        //String sessionId2 = headerAccessor.getSessionAttributes().get("S")
        User user = onlineUserMap.get(sessionId);
        message.setFrom(user.getUserName());
        ws.sendTopicChat(message);
    }
}
