package cs.club.mojuk.menu.history;

import cs.club.mojuk.entity.ManageYear;
import cs.club.mojuk.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String history(Model model) {
        List<ManageYear> manageYears = historyService.getAllManageYears();
        model.addAttribute("manageYears", manageYears);
        return "history/index";
    }

    @ResponseBody
    @GetMapping("/history/list")
    public List<Student> getHistory(@RequestParam int manageYear) {
        return historyService.getStudentsByManageYear(manageYear);
    }
}
