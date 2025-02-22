package cs.club.mojuk.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cs.club.mojuk.menu.talk.TalkMessage;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Entity
@Table(name = "talk_room")
@Getter
@Setter
@NoArgsConstructor
public class TalkRoom {

    @Id
    @Column(name = "roomId")
    private String roomId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Transient
    private final Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    // roomId를 매개변수로 받는 생성자
    public TalkRoom(String roomId,
                    String email,
                    String password) {
        this.roomId = roomId;
        this.email = email;
        this.password = password;
    }

    public void handleMessage(WebSocketSession session, TalkMessage message, ObjectMapper objectMapper) {
        // 메시지를 현재 방의 모든 세션에 메시지 전송 로직(JSON 문자열로 변환)
        String payload;

        try {
            payload = objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }

        // TextMessage 객체 생성
        TextMessage textMessage = new TextMessage(payload);

        // 현재 방의 모든 세션에 메시지 전송
        sessions.parallelStream().forEach(s -> {
            if (s.isOpen()) {
                try {
                    s.sendMessage(textMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // 세션이 닫혀 있는 경우 처리 로직
                System.out.println("세션이 닫혀 있습니다: " + s.getId());
            }
        });
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
}