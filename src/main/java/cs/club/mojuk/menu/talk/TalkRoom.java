package cs.club.mojuk.menu.talk;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.WebSocketSession;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter
public class TalkRoom {
    private String roomId;
    private Set<WebSocketSession> sessions = ConcurrentHashMap.newKeySet();

    // roomId를 매개변수로 받는 생성자
    public TalkRoom(String roomId) {
        this.roomId = roomId;
    }

    public void handleMessage(WebSocketSession session, TalkMessage message) {
        // 메시지를 현재 방의 모든 세션에 전송
        sessions.parallelStream().forEach(s -> {
            // 메시지 전송 로직
        });
    }

    public void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    public void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }
}