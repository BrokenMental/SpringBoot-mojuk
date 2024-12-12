package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "level")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private int label;
    @Column(length = 10)
    private String varchar;
    private int auto_increment;
    @Column(length = 200)
    private String comment;
}
