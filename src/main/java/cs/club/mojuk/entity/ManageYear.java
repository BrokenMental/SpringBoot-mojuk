package cs.club.mojuk.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manage_year")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ManageYear {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manage_year")
    private int manageYear;
}
