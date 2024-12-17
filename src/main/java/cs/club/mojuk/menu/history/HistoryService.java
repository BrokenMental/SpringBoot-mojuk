package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Level;
import cs.club.mojuk.entity.ManageYear;
import cs.club.mojuk.entity.Student;
import cs.club.mojuk.repository.LevelRepository;
import cs.club.mojuk.repository.ManageYearRepository;
import cs.club.mojuk.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return studentRepository.findByManageYear(manageYear);
    }

    public List<Student> getStudentsByManageYearAndLevelIdx(int manageYear, int levelIdx) {
        return studentRepository.findByManageYearAndLevel_idx(manageYear, levelIdx);
    }

    public Optional<Level> getLevelByIdx(int levelIdx) {
        return levelRepository.findByIdx(levelIdx);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ManageYear> getAllManageYears() {
        return manageYearRepository.findAllByOrderByManageYearDesc();
    }

    public int getLatestManageYear() {
        List<ManageYear> manageYears = getAllManageYears();
        if (!manageYears.isEmpty()) {
            return manageYears.get(0).getManageYear();
        }
        return 0; // 기본값 0 반환
    }
}
