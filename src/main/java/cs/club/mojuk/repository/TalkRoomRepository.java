package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TalkRoomRepository extends JpaRepository<TalkRoom, String> {
    default TalkRoom findOrCreateRoom(String roomId) {
        return findById(roomId).orElseGet(() -> {
            TalkRoom newRoom = new TalkRoom(roomId);
            save(newRoom);
            return newRoom;
        });
    }
}
