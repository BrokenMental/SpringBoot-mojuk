package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CachedTalkRoomRepository {
    private final TalkRoomRepository talkRoomRepository;
    private final ConcurrentHashMap<String, TalkRoom> cache = new ConcurrentHashMap<>();

    public CachedTalkRoomRepository(TalkRoomRepository talkRoomRepository) {
        this.talkRoomRepository = talkRoomRepository;
    }

    public TalkRoom findOrCreateRoom(String roomId) {
        /*return cache.computeIfAbsent(roomId, id ->
            talkRoomRepository.findById(id).orElse(new TalkRoom(roomId))
        );*/
        return null;
    }

    public void clearRoomCache(String roomId) {
        cache.remove(roomId);
    }

    public List<TalkRoom> findAllRooms() {
        return new ArrayList<>(cache.values()); // 모든 채팅방 반환
    }
}
