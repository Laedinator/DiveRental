package nl.miwnn.se12.marc.DiveEquipmentRental.repository;

import nl.miwnn.se12.marc.DiveEquipmentRental.model.RentalUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
public interface RentalUserRepository extends JpaRepository<RentalUser, Long> {
    Optional<RentalUser> findRentalUserByUsername(String username);
}
