package cs.club.mojuk.repository;

import cs.club.mojuk.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<Level, Integer> {
    Optional<Level> findByIdx(int idx);
}
