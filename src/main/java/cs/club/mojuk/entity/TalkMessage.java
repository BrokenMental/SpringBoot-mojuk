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
    private Long id;  // 기본 키 (PK)

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false)
    private String message;

    public TalkMessage(String roomId, String message) {
        this.roomId = roomId;
        this.message = message;
    }
}
