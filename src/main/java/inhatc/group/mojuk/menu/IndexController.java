package inhatc.group.mojuk.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
    @GetMapping(value={"/", "/index"})
    public String indexView() {
        return "/index";
    }

    @GetMapping(value="/signOut")
    public String signOut(HttpSession session) {
        //세션 제거
        session.invalidate();

        return "redirect:/";
    }
}
