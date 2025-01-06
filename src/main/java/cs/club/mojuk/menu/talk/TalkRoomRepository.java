package cs.club.mojuk.menu.talk;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TalkRoomRepository {
    private Map<String, TalkRoom> talkRoomMap = new ConcurrentHashMap<>();

    public TalkRoom createTalkRoom(String roomId) {
        TalkRoom talkRoom = new TalkRoom(roomId);
        talkRoomMap.put(roomId, talkRoom);
        return talkRoom;
    }

    public TalkRoom findRoomById(String roomId) {
        return talkRoomMap.get(roomId);
    }
}
