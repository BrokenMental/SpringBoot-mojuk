package cs.club.mojuk.menu.talk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TalkController {

    public TalkController() {

    }

    @GetMapping("/talk")
    public String history() {
        return "talk/index";
    }
}
