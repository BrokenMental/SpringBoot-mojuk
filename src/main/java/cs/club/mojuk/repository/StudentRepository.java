package cs.club.mojuk.repository;

import cs.club.mojuk.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByManageYear(int manageYear);
    List<Student> findByManageYearAndLevel_idx(int manageYear, int levelIdx);
    List<Student> findAll();
}
