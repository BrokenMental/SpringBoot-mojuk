package cs.club.mojuk.menu.history;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @GetMapping("/history/list")
    public ResponseEntity<String> getHistory(@RequestParam("manageYear") String manageYear) {
        return null;
    }
}
