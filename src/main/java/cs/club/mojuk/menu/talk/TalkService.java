package cs.club.mojuk.menu.talk;

import cs.club.mojuk.entity.TalkRoom;
import cs.club.mojuk.repository.CachedTalkRoomRepository;
import cs.club.mojuk.repository.TalkRoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TalkService {
    private final CachedTalkRoomRepository cachedTalkRoomRepository;
    private final TalkRoomRepository talkRoomRepository;

    public TalkService(CachedTalkRoomRepository cachedTalkRoomRepository, TalkRoomRepository talkRoomRepository) {
        this.cachedTalkRoomRepository = cachedTalkRoomRepository;
        this.talkRoomRepository = talkRoomRepository;
    }

    public TalkRoom findOrCreateRoom(String roomId) {
        return cachedTalkRoomRepository.findOrCreateRoom(roomId);
    }

    public void clearRoom(String roomId) {
        cachedTalkRoomRepository.clearRoomCache(roomId);
    }

    public List<TalkRoom> getAllRooms() {
        return cachedTalkRoomRepository.findAllRooms();
    }

    public TalkRoom createRoom(String email, String password) {
        String roomId;
        do {
            roomId = generateRandomRoomId();
        } while (talkRoomRepository.existsByRoomId(roomId));

        if(talkRoomRepository.existsByRoomId(roomId)) {
            throw new RuntimeException("이미 생성된 방이 있습니다.");
        }

        TalkRoom talkRoom = new TalkRoom(roomId, email, password);

        return talkRoomRepository.save(talkRoom);
    }

    public TalkRoom joinRoom(String roomId, String password) {
        TalkRoom talkRoom = talkRoomRepository.findByRoomId(roomId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 방입니다."));

        if(!talkRoom.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return talkRoom;
    }

    private String generateRandomRoomId() {
        //System.currentTimeMillis();
        return UUID.randomUUID().toString().substring(0, 8);
    }
}
