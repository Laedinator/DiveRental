package nl.miwnn.se12.marc.DiveEquipmentRental.seed;

import lombok.RequiredArgsConstructor;
import net.datafaker.Faker;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Rental;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.EquipmentRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.RentalRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.CertificationService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
@Component
@RequiredArgsConstructor
public class Seeder {
    private final CertificationService certificationService;
    private final CertificationRepository certificationRepository;
    private final EquipmentRepository equipmentRepository;
    private final RentalRepository rentalRepository;
    private final DiverRepository diverRepository;
    private final PasswordEncoder passwordEncoder;

    Faker faker = new Faker();

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        seedCertifications();
        seedEquipment();
        SeedRentals();
        SeedDivers();
    }

    private void SeedDivers() {
        if (diverRepository.findAll().size() < 3) {
            List<Diver> divers = new ArrayList<>();
            divers.add(new Diver("Max", "H", "Meier", certificationRepository.findById(5L).get(),
                    "max", passwordEncoder.encode("test")));
            divers.add(new Diver("Ursula", "", "Meier", certificationRepository.findById(5L).get(),
                    "ursula", passwordEncoder.encode("test")));
            divers.add(new Diver("Kurt", "", "Meier", certificationRepository.findById(2L).get(),
                    "kurt", passwordEncoder.encode("test")));
            divers.get(0).setEmployee(true);
            divers.get(0).setAdmin(true);
            divers.get(0).addCertification(certificationRepository.findById(4L).get());
            divers.get(1).setEmployee(true);
            divers.get(1).setAdmin(true);
            divers.get(0).addCertification(certificationRepository.findById(3L).get());
            divers.get(2).setEmployee(true);
            divers.get(2).addCertification(certificationRepository.findById(2L).get());

            diverRepository.saveAll(divers);
        }

        if (diverRepository.findAll().size() < 10) {
            for (int index = 0; index < 5; index++) {
                String newFirstName = faker.name().firstName();
                String newMiddleName = (index % 3 == 0) ? faker.name().suffix() : "";
                String newLastName = faker.name().lastName();
                String password = passwordEncoder.encode("test");
                diverRepository.save(new Diver(newFirstName, newMiddleName, newLastName,
                        certificationRepository.findById((long) (index + 1)).get(), newFirstName, password));
            }
        }

    }

    private void SeedRentals() {
        if (rentalRepository.findAll().size() < 10) {
            List<Rental> rentals = new ArrayList<>();
            rentals.add(new Rental(equipmentRepository.findById(1L).get()));
            rentals.add(new Rental(equipmentRepository.findById(1L).get()));
            rentals.add(new Rental(equipmentRepository.findById(2L).get()));
            rentals.add(new Rental(equipmentRepository.findById(3L).get()));
            rentals.add(new Rental(equipmentRepository.findById(3L).get()));
            rentals.add(new Rental(equipmentRepository.findById(4L).get()));
            rentals.add(new Rental(equipmentRepository.findById(4L).get()));
            rentals.add(new Rental(equipmentRepository.findById(4L).get()));
            rentals.add(new Rental(equipmentRepository.findById(5L).get()));
            rentals.add(new Rental(equipmentRepository.findById(5L).get()));
            rentalRepository.saveAll(rentals);
        }
    }

    private void seedEquipment() {
        if (equipmentRepository.findAll().size() < 5) {
            List<Equipment> equipment = new ArrayList<>();
            equipment.add(new Equipment("Scorpion 2", "BCD", "XL", "Cressi", certificationRepository.findById(2L).get()));
            equipment.add(new Equipment("Scorpion 1", "BCD", "L", "Cressi", certificationRepository.findById(2L).get()));
            equipment.add(new Equipment("M1", "Mask", "s", "Aqualung", certificationRepository.findById(1L).get()));
            equipment.add(new Equipment("M2", "Mask", "L", "Aqualung", certificationRepository.findById(1L).get()));
            equipment.add(new Equipment("Nitrox 21", "Tank", "12l", "Roth", certificationRepository.findById(4L).get()));
            equipmentRepository.saveAll(equipment);
        }
    }

    private void seedCertifications() {
        if (certificationService.getAllCertifications().size() < 5) {
            List<CertificationDto> certifications = new ArrayList<>();
            certifications.add(CertificationDto.builder().certificationId(1L).certificationName("None").build());
            certifications.add(CertificationDto.builder().certificationId(2L).certificationName("OWD").build());
            certifications.add(CertificationDto.builder().certificationId(3L).certificationName("AOWD").build());
            certifications.add(CertificationDto.builder().certificationId(4L).certificationName("Nitrox").build());
            certifications.add(CertificationDto.builder().certificationId(5L).certificationName("Instructor").build());
            certificationService.saveOrUpdateCertification(certifications);
        }
    }
}
