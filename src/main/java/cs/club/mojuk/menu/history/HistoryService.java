package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Level;
import cs.club.mojuk.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    private final StudentRepository studentRepository;
    private final LevelRepository levelRepository;

    public HistoryService(
            StudentRepository studentRepository,
            LevelRepository levelRepository
    ) {
        this.studentRepository = studentRepository;
        this.levelRepository = levelRepository;
    }

    public List<Student> getStudentsByManageYear(int manageYear) {
        return studentRepository.findByManage_year(manageYear);
    }

    public List<Student> getStudentsByManageYearAndLevelIdx(int manageYear, int levelIdx) {
        return studentRepository.findByManage_yearAndLevel_idx(manageYear, levelIdx);
    }

    public Optional<Level> getLevelByIdx(int levelIdx) {
        return levelRepository.findByIdx(levelIdx);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public int getLatestManageYear() {
        Student latestStudent = studentRepository.findTopByOrderByManage_yearDesc();
        return latestStudent.getManage_year();
    }
}
