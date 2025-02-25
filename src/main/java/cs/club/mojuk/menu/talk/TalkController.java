package cs.club.mojuk.menu.talk;

import cs.club.mojuk.dto.TalkRoomRequest;
import cs.club.mojuk.dto.TalkRoomResponse;
import cs.club.mojuk.entity.TalkRoom;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public ResponseEntity<TalkRoomResponse> joinRoom(@RequestBody TalkRoomRequest request) {
        // Request 객체로 변경하여 JSON 요청을 처리할 수 있도록 함
        return ResponseEntity.ok(talkService.joinRoom(request.roomId(), request.password()));
    }
}