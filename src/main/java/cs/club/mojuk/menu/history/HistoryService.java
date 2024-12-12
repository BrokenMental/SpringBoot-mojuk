package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Level;
import cs.club.mojuk.entity.ManageYear;
import cs.club.mojuk.entity.Student;
import cs.club.mojuk.repository.LevelRepository;
import cs.club.mojuk.repository.ManageYearRepository;
import cs.club.mojuk.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    private final StudentRepository studentRepository;
    private final LevelRepository levelRepository;
    private final ManageYearRepository manageYearRepository;

    public HistoryService(
            StudentRepository studentRepository,
            LevelRepository levelRepository,
            ManageYearRepository manageYearRepository
    ) {
        this.studentRepository = studentRepository;
        this.levelRepository = levelRepository;
        this.manageYearRepository = manageYearRepository;
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

    public List<ManageYear> getAllManageYears() {
        return manageYearRepository.findAllByOrderByYearDesc();
    }

    public LocalDateTime getLatestManageYear() {
        List<ManageYear> manageYears = getAllManageYears();
        if (!manageYears.isEmpty()) {
            return manageYears.get(0).getManage_year();
        }
        return null; // 기본값 null 반환
    }
}
