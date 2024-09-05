package cs.club.mojuk.menu.history;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HistoryController {

    HistoryController() {

    }

    @GetMapping("/history")
    public String history() {
        return "history/index";
    }
}
