package nl.miwnn.se12.marc.DiveEquipmentRental.repository;

import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    Optional<Equipment> findEquipmentByName(String name);
}
