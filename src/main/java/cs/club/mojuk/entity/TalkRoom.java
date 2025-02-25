package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "talk_room")
@Getter
@Setter
@NoArgsConstructor
public class TalkRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 기본 키 (postgreSQL 에선 serial4 타입, 시퀀스 설정과 해당 설정을 함께 해줌)

    @Column(nullable = false)
    private String roomId;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // roomId를 매개변수로 받는 생성자
    public TalkRoom(String roomId,
                    String email,
                    String password) {
        this.roomId = roomId;
        this.email = email;
        this.password = password;
    }
}