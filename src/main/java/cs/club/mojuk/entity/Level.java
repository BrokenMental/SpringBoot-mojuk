package cs.club.mojuk.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "level")
public record Level(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idx,
    int label,
    @Column(length = 10)
    String varchar,
    int auto_increment,
    @Column(length = 200)
    String comment,

    @OneToMany(mappedBy = "level")
    List<Student> students
) {
}
