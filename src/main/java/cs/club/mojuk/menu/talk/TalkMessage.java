package cs.club.mojuk.menu.talk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TalkMessage {
    private String type;
    private String roomId;
    private String sender;
    private String content;
}
