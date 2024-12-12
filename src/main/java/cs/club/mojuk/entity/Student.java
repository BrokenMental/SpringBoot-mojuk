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
    private int manage_year;
    @ManyToOne
    @JoinColumn(name = "level_idx")
    private Level level;
    private int entrance_year;
    private String name;
    private String git;
    private String homepage;
    private LocalDateTime mod_date;
    private LocalDateTime reg_date;
    private LocalDateTime current_timestamp;
}