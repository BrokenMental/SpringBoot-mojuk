package cs.club.mojuk.menu.talk;

import cs.club.mojuk.entity.TalkRoom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TalkController {

    private final TalkService talkService;

    public TalkController(TalkService talkService) {
        this.talkService = talkService;
    }

    @GetMapping("/talk")
    public String history() {
        return "talk/index";
    }

    @ResponseBody
    @GetMapping("/talk/rooms")
    public List<TalkRoom> getAllRooms() {
        return talkService.getAllRooms();
    }

    @ResponseBody
    @PostMapping("/talk/create/room")
    public TalkRoom createRoom(@RequestBody TalkRoom room) {
        return null;
        //return talkService.createRoom();
    }
}
