package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.Level;
import cs.club.mojuk.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;

    public HistoryService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public List<Level> getAllLevels() {
        return historyRepository.findAllLevel();
    }

    //manage year에 따른 학생 검색
    public List<Student> getStudentsByManageYear(int manageYear) {
        return historyRepository.findByManage_year(manageYear);
    }

    //manage year와 level에 따른 학생 검색
    public List<Student> getStudentsByManageYearAndLevelIdx(int manageYear, int levelIdx) {
        return historyRepository.findByManage_yearAndLevel_idx(manageYear, levelIdx);
    }

    //모든 학생 검색
    public List<Student> getAllStudents() {
        return historyRepository.findAll();
    }
}
