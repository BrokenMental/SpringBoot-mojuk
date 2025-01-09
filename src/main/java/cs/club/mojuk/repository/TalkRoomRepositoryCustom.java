package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;

public interface TalkRoomRepositoryCustom {
    TalkRoom findOrCreateRoom(String roomId);
}
