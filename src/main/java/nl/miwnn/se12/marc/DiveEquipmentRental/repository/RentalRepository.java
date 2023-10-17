package nl.miwnn.se12.marc.DiveEquipmentRental.repository;

import nl.miwnn.se12.marc.DiveEquipmentRental.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
