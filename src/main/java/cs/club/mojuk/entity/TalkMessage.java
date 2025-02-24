package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TalkMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roomId;

    @Column(nullable = false)
    private String message;

    public TalkMessage(String message) {
        this.message = message;
    }
}
