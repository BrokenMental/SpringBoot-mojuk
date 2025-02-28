package cs.club.mojuk.menu.talk;

import cs.club.mojuk.dto.TalkRoomRequest;
import cs.club.mojuk.dto.TalkRoomResponse;
import cs.club.mojuk.entity.TalkRoom;
import cs.club.mojuk.repository.CachedTalkRoomRepository;
import cs.club.mojuk.repository.TalkMessageRepository;
import cs.club.mojuk.repository.TalkRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TalkService {
    private final CachedTalkRoomRepository cachedTalkRoomRepository;
    private final TalkRoomRepository talkRoomRepository;
    private final TalkMessageRepository talkMessageRepository;

    public TalkRoom findOrCreateRoom(String roomId) {
        return cachedTalkRoomRepository.findOrCreateRoom(roomId);
    }

    public void clearRoom(String roomId) {
        cachedTalkRoomRepository.clearRoomCache(roomId);
    }

    public List<TalkRoom> getAllRooms() {
        return cachedTalkRoomRepository.findAllRooms();
    }

    @Transactional
    public TalkRoomResponse createRoom(TalkRoomRequest request) {
        String roomId;
        do {
            roomId = generateRandomRoomId();
        } while (talkRoomRepository.existsByRoomId(roomId));

        if(talkRoomRepository.existsByRoomId(roomId)) {
            throw new RuntimeException("이미 생성된 방이 있습니다.");
        }

        TalkRoom talkRoom = new TalkRoom(roomId, request.email(), request.password());
        talkRoomRepository.save(talkRoom);

        return new TalkRoomResponse(roomId, "방이 생성되었습니다.", managerYN(request, talkRoom));
    }

    public TalkRoomResponse joinRoom(TalkRoomRequest request) {
        TalkRoom talkRoom = talkRoomRepository.findByRoomId(request.roomId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다."));

        if (!talkRoom.getPassword().equals(request.password())) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return new TalkRoomResponse(request.roomId(), "방에 입장했습니다.", managerYN(request, talkRoom));
    }

    public List<Object> getChatHistory(String roomId) {
        return talkMessageRepository.findByRoomIdOrderByIdAsc(roomId)
                .stream()
                .map(msg -> {
                    Map<String, Object> result = new HashMap<>();
                    result.put("sender", msg.getSender() != null ? msg.getSender() : "system");
                    result.put("message", msg.getMessage());
                    result.put("timestamp", msg.getCreatedAt());
                    return result;
                })
                .collect(Collectors.toList());
    }

    private String generateRandomRoomId() {
        //System.currentTimeMillis();
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private int managerYN(TalkRoomRequest request, TalkRoom talkRoom) {
        return request.email().equals(talkRoom.getEmail()) ? 1 : 0;
    }
}
