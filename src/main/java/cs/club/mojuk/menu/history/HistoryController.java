package cs.club.mojuk.menu.history;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryController {

    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @GetMapping("/history")
    public String history(Model model) {
        return "history/index";
    }

    @ResponseBody
    @GetMapping("/history/list")
    public ResponseEntity<String> getHistory(@RequestParam("manageYear") Integer manageYear) {
        if(manageYear == null) {
            manageYear = 2024;
        }


        return null;
    }
}
