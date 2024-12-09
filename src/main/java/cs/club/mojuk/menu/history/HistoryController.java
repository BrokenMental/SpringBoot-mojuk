package cs.club.mojuk.menu.history;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HistoryController {

    HistoryController() {

    }

    @GetMapping("/history")
    public String history() {
        return "history/index";
    }

    @ResponseBody
    @GetMapping("/")
    public ResponseEntity<String> getHistory() {
        return null;
    }
}
