package cs.club.mojuk.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "student")
public record Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx,
    int manage_year,
    Date date,
    @Column
    Integer level_idx,
    @Column(length = 200)
    String name,
    @Column(length = 200)
    String git,
    @Column(length = 200)
    String homepage,
    @Column
    Date mod_date,
    @Column
    Date reg_date,
    @Column(length = 200)
    String current_timestamp,

    @ManyToOne                       // 다대일 관계 매핑
    @JoinColumn(name = "level_idx")
    Level level
) {
}