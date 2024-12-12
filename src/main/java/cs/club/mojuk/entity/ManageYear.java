package cs.club.mojuk.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "manage_year")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManageYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private LocalDateTime manage_year;
}
