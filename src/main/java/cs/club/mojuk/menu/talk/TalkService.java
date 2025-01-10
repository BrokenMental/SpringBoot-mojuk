package cs.club.mojuk.menu.talk;

import cs.club.mojuk.entity.TalkRoom;
import cs.club.mojuk.repository.CachedTalkRoomRepository;
import org.springframework.stereotype.Service;

@Service
public class TalkService {
    private final CachedTalkRoomRepository cachedTalkRoomRepository;

    public TalkService(CachedTalkRoomRepository cachedTalkRoomRepository) {
        this.cachedTalkRoomRepository = cachedTalkRoomRepository;
    }

    public TalkRoom findOrCreateRoom(String roomId) {
        return cachedTalkRoomRepository.findOrCreateRoom(roomId);
    }

    public void clearRoom(String roomId) {
        cachedTalkRoomRepository.clearRoomCache(roomId);
    }
}
