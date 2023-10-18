package nl.miwnn.se12.marc.DiveEquipmentRental.repository;

import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
public interface CertificationRepository extends JpaRepository<Certification, Long>   {
}
