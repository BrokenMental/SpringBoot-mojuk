package cs.club.mojuk.menu.talk;

import cs.club.mojuk.dto.TalkRoomRequest;
import cs.club.mojuk.dto.TalkRoomResponse;
import cs.club.mojuk.entity.TalkRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TalkController {

    private final TalkService talkService;

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
    @PostMapping("/talk/room/create")
    public ResponseEntity<TalkRoomResponse> createRoom(@RequestBody TalkRoomRequest request) {
        return ResponseEntity.ok(talkService.createRoom(request));
    }

    @ResponseBody
    @PostMapping("/talk/room/join")
    public ResponseEntity<TalkRoomResponse> joinRoom(@RequestParam String roomId,
                                                     @RequestParam String password) {
        return ResponseEntity.ok(talkService.joinRoom(roomId, password));
    }
}
