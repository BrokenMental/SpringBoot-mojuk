package cs.club.mojuk.repository;

import cs.club.mojuk.entity.ManageYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManageYearRepository extends JpaRepository<ManageYear, Integer> {
    List<ManageYear> findAllByOrderByManageYearDesc();
}