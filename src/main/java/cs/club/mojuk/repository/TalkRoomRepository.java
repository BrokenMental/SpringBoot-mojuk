package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TalkRoomRepository extends JpaRepository<TalkRoom, String> {
}
