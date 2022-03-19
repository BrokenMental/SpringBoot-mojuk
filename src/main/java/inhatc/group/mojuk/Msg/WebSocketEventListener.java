package inhatc.group.mojuk.Msg;

import inhatc.group.mojuk.Model.ChatMessage;
import inhatc.group.mojuk.Model.MessageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

@Component
public class WebSocketEventListener {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);

    /*
     * STOMP와 같은 단순 메시징 프로토콜에서 Spring과 함께 사용하기 위한 인터페이스
     * 일반적으로, Websocket session으로 인증되거나 session을 시작한 핸드쉐이크 요청으로 인증된 사용자에게 전달
     * session 인증이 되지 않았을경우 sessionId를 활용해 전달할 수도 있음
     */
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
    
    /*
     * 아래 두 메소드의 Session~ 인수는 AbstractSubProtocolEvent Class를 상속 받아 작성 됨
     * 유저는 현재 서버의 SessionId로 확인
     */

    @EventListener //session에 유저가 추가되면 발생하는 이벤트
    public void WebSocketConnect(SessionConnectedEvent event) {
        logger.info("Received a new web socket connection");
    }

    @EventListener //session에서 유저가 사라지면 발생하는 이벤트
    public void WebSocketDisconnect(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username != null) {
            logger.info("User Disconnected : " + username);

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setType(MessageType.LEAVE);
            chatMessage.setSender(username);

            messagingTemplate.convertAndSend("/topic/public", chatMessage);
        }
    }
}