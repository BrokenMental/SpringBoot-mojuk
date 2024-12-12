package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByManage_year(int manageYear);
    List<Student> findByManage_yearAndLevel_idx(int manageYear, int levelIdx);
    List<Student> findAll();
    Student findTopByOrderByManage_yearDesc();
}
