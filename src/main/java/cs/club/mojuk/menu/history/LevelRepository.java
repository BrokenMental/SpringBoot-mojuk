package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Level;
import cs.club.mojuk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LevelRepository extends JpaRepository<Student, Integer> {
    Optional<Level> findByIdx(int idx);
}
