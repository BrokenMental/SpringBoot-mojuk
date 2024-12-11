package cs.club.mojuk.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private int manage_year;
    private Date date;
    @Column(nullable = true)
    private Integer level_idx;
    @Column(nullable = true, length = 200)
    private String name;
    @Column(nullable = true, length = 200)
    private String git;
    @Column(nullable = true, length = 200)
    private String homepage;
    @Column(nullable = true)
    private Date mod_date;
    @Column(nullable = true)
    private Date reg_date;
    @Column(nullable = true, length = 200)
    private String current_timestamp;
}