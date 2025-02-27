package cs.club.mojuk.config.webSocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs.club.mojuk.entity.TalkMessage;
import cs.club.mojuk.repository.TalkMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class WebSocketTalkHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper;
    private final TalkMessageRepository talkMessageRepository;

    // roomId를 키로 사용하여 해당 방의 WebSocketSession 목록을 관리
    private final Map<String, List<WebSocketSession>> roomSessions = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        // 연결 시 roomId 추출
        String roomId = extractRoomId(session);

        // 방에 세션 추가
        roomSessions.computeIfAbsent(roomId, key -> new ArrayList<>()).add(session);

        System.out.println("WebSocket 연결됨 - 방 ID: " + roomId + ", 세션 ID: " + session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 메시지를 JSON으로 파싱
        Map<String, Object> messageMap = objectMapper.readValue(message.getPayload(), Map.class);

        // 메시지에서 방 ID 추출
        String roomId = (String) messageMap.get("roomId");

        // 세션의 URL에서 추출한 roomId와 비교하여 확인
        String sessionRoomId = extractRoomId(session);
        if (!sessionRoomId.equals(roomId)) {
            System.out.println("세션의 방 ID(" + sessionRoomId + ")와 메시지의 방 ID(" + roomId + ")가 일치하지 않습니다.");
            roomId = sessionRoomId; // 세션의 roomId를 우선 사용
        }

        // 보낸 사람 정보 및 내용 추출
        String sender = (String) messageMap.get("sender");
        String msg = (String) messageMap.get("message");

        // 메시지 객체 생성
        TalkMessage talkMessage = new TalkMessage(roomId, sender, msg);

        // DB에 채팅 저장
        talkMessageRepository.save(talkMessage);

        // 클라이언트에게 보낼 메시지 생성
        String outputMessage = objectMapper.writeValueAsString(
                Map.of(
                        "sender", sender,
                        "message", msg,
                        "timestamp", talkMessage.getCreatedAt()
                )
        );

        // 같은 방에 있는 모든 세션에 메시지 전달
        sendMessageToRoom(roomId, new TextMessage(outputMessage));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        // 세션이 종료될 때 roomId 목록에서 제거
        String roomId = extractRoomId(session);
        List<WebSocketSession> sessions = roomSessions.get(roomId);

        if (sessions != null) {
            sessions.remove(session);

            // 방에 더 이상 세션이 없으면 방 제거
            if (sessions.isEmpty()) {
                roomSessions.remove(roomId);
                System.out.println("방 제거됨 - 방 ID: " + roomId);
            }
        }

        System.out.println("WebSocket 연결 종료 - 방 ID: " + roomId + ", 세션 ID: " + session.getId());
    }

    // URL 경로에서 roomId 추출
    private String extractRoomId(WebSocketSession session) {
        String path = session.getUri().getPath();
        String[] pathSegments = path.split("/");

        // URL 패턴이 /ws/talk/{roomId}이므로 마지막 세그먼트가 roomId
        if (pathSegments.length > 0) {
            return pathSegments[pathSegments.length - 1];
        }

        throw new IllegalArgumentException("roomId를 찾을 수 없습니다: " + path);
    }

    // 특정 방의 모든 세션에 메시지 전송
    private void sendMessageToRoom(String roomId, TextMessage message) {
        List<WebSocketSession> sessions = roomSessions.getOrDefault(roomId, Collections.emptyList());

        sessions.forEach(session -> {
            try {
                if (session.isOpen()) {
                    session.sendMessage(message);
                }
            } catch (IOException e) {
                System.err.println("메시지 전송 오류: " + e.getMessage());
            }
        });
    }
}