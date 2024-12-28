package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.ManageYear;
import cs.club.mojuk.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public String history() {
        return "history/index";
    }

    @ResponseBody
    @GetMapping("/history/manageYears")
    public List<ManageYear> getManageYears() {
        List<ManageYear> tempList = historyService.getAllManageYears();
        return tempList;
    }

    @ResponseBody
    @GetMapping("/history/studentList")
    public List<Student> getStudentList(@RequestParam("manageYear") int manageYear) {
        return historyService.getStudentsByManageYear(manageYear);
    }
}
