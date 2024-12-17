package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "manage_year")
    private int manageYear;
    @ManyToOne
    @JoinColumn(name = "level_idx")
    private Level level;
    @Column(name = "entrance_year")
    private int entranceYear;
    private String name;
    private String git;
    private String homepage;
    private LocalDateTime mod_date;
    private LocalDateTime reg_date;
}