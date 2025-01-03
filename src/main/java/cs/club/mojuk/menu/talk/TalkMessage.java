package cs.club.mojuk.menu.talk;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TalkMessage {
    private String roomId;
    private String sender;
    private String content;
}
