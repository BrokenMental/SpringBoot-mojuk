package cs.club.mojuk.config.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.club.mojuk.entity.TalkMessage;
import cs.club.mojuk.menu.talk.TalkService;
import cs.club.mojuk.repository.TalkMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class WebSocketTalkHandler extends TextWebSocketHandler {
    private final TalkService talkService;
    private final ObjectMapper objectMapper;
    private final TalkMessageRepository talkMessageRepository;
    private final Map<String, List<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String roomId = extractRoomId(session);
        roomSessions.computeIfAbsent(roomId, key -> new ArrayList<>()).add(session);
        System.out.println("WebSocket 연결됨: " + roomId);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        //방 이름 확인
        String roomId = extractRoomId(session);

        /*
        List<WebSocketSession> sessions = roomSessions.getOrDefault(roomId, new ArrayList<>());

        for (WebSocketSession s : sessions) {
            s.sendMessage(message);
        }
        */
        TalkMessage talkMessage = objectMapper.readValue(message.getPayload(), TalkMessage.class);

        // DB에 채팅 저장
        talkMessageRepository.save(new TalkMessage(roomId, talkMessage.getMessage()));

        // 같은 방의 모든 세션에 메시지 전달
        for (WebSocketSession s : roomSessions.getOrDefault(roomId, Collections.emptyList())) {
            s.sendMessage(new TextMessage(talkMessage.getMessage()));
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {

        String roomId = extractRoomId(session);
        roomSessions.getOrDefault(roomId, new ArrayList<>()).remove(session);
        System.out.println("WebSocket 연결 종료: " + roomId);
    }

    // 세션의 속성에서 roomId 조회
    private String getRoomId(WebSocketSession session) {
        Map<String, Object> attributes = session.getAttributes();
        return (String) attributes.get("roomId");
    }

    private String extractRoomId(WebSocketSession session) {
        /*
        String path = session.getUri().getPath();
        return path.substring(path.lastIndexOf('/') + 1);
        */

        // 1. 세션의 URI에서 쿼리 파라미터 추출
        String query = session.getUri().getQuery();

        // 2. URI가 null이 아니고 "roomId="로 시작하는지 확인 후 추출
        if (query != null && query.startsWith("roomId=")) {
            return query.substring(7); // "roomId=" 이후의 값 반환
        }

        throw new IllegalArgumentException("roomId를 찾을 수 없습니다.");
    }
}