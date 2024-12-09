package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<Student, Integer> {
}
