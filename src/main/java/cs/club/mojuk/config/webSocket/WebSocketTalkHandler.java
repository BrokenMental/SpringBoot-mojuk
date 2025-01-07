package cs.club.mojuk.config.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.club.mojuk.entity.TalkRoom;
import cs.club.mojuk.menu.talk.TalkMessage;
import cs.club.mojuk.repository.TalkRoomRepository;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WebSocketTalkHandler extends TextWebSocketHandler {
    private final TalkRoomRepository talkRoomRepository;
    private final ObjectMapper objectMapper;
    private final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    public WebSocketTalkHandler(TalkRoomRepository talkRoomRepository, ObjectMapper objectMapper) {
        this.talkRoomRepository = talkRoomRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        TalkMessage talkMessage = objectMapper.readValue(message.getPayload(), TalkMessage.class);
        TalkRoom talkRoom = talkRoomRepository.findOrCreateRoom(talkMessage.getRoomId());
        talkRoom.handleMessage(session, talkMessage);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}