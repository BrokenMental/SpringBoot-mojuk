package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TalkMessageRepository extends JpaRepository<TalkMessage, Long> {
    List<TalkMessage> findByRoomIdOrderByIdAsc(String roomId);
}
