package cs.club.mojuk.menu;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping(value={"/", "/index"})
    public String index() {
        return "/index";
    }

    @GetMapping(value="/select")
    public String select() {
        return "/select";
    }

    @GetMapping(value="/signOut")
    public String signOut(HttpSession session) {
        //세션 제거
        session.invalidate();

        return "redirect:/";
    }
}
