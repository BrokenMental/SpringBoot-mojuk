package cs.club.mojuk.repository;

import cs.club.mojuk.entity.ManageYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ManageYearRepository extends JpaRepository<ManageYear, Integer> {
    List<ManageYear> findAllByOrderByYearDesc();
}