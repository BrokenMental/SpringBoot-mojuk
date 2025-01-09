package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;
import jakarta.persistence.EntityManager;

public class TalkRoomRepositoryImpl {
    private final EntityManager entityManager;
    public TalkRoomRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public TalkRoom findOrCreateRoom(String roomId) {
        //objectMapper 를 함께 전달하며 순환 반복을 방지할 경우 사용
        TalkRoom talkRoom = entityManager.find(TalkRoom.class, roomId);
        if (talkRoom == null) {
            talkRoom = new TalkRoom(roomId);
            entityManager.persist(talkRoom);
        }
        return talkRoom;
    }
}
