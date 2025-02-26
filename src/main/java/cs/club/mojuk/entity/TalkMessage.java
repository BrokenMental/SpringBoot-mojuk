package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TalkMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키 (PK)

    @Column(nullable = false)
    private String roomId;

    @Column
    private String sender;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public TalkMessage(String roomId, String message) {
        this.roomId = roomId;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }

    public TalkMessage(String roomId, String sender, String message) {
        this.roomId = roomId;
        this.sender = sender;
        this.message = message;
        this.createdAt = LocalDateTime.now();
    }
}
