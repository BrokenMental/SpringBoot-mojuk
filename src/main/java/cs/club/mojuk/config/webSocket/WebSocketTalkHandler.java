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
        //sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        // 클라이언트로부터 받은 메시지를 TalkMessage 객체로 변환
        TalkMessage talkMessage = objectMapper.readValue(message.getPayload(), TalkMessage.class);

        //방 이름 확인
        String roomId = talkMessage.getRoomId();

        //해당 방이 존재하지 않으면 생성
        TalkRoom talkRoom = talkRoomRepository.findOrCreateRoom(roomId);

        if ("JOIN".equals(talkMessage.getType())) {
            //sessions.remove(session);
            talkRoom.addSession(session);
            // 세션에 roomId 저장
            session.getAttributes().put("roomId", roomId);

            // 클라이언트에게 방 참여 성공 메시지 전송 등 추가 처리
        }

        talkRoom.handleMessage(session, talkMessage, objectMapper);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        // 세션의 속성에서 roomId 조회
        String roomId = (String) session.getAttributes().get("roomId");

        if (roomId != null) {
            // 해당 방에서 세션 제거 로직
            TalkRoom talkRoom = talkRoomRepository.findById(roomId).orElse(null);
            if (talkRoom != null) {
                talkRoom.removeSession(session);
            }
        }
    }
}