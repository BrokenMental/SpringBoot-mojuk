package cs.club.mojuk.menu.talk;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class TalkRoom {
    private String id;
    private String name;

    public TalkRoom(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
