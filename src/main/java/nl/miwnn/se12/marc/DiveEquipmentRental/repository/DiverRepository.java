package nl.miwnn.se12.marc.DiveEquipmentRental.repository;

import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DiverRepository extends JpaRepository<Diver, Long> {
    Optional<Diver> findRentalUserByUsername(String username);
}
