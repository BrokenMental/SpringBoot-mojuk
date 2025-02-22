package cs.club.mojuk.repository;

import cs.club.mojuk.entity.TalkRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TalkRoomRepository extends JpaRepository<TalkRoom, String> {
    boolean existsByRoomId(String roomId);
    boolean existsByEmail(String email);
    Optional<TalkRoom> findByRoomId(String roomId);
}
