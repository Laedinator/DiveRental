package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Rental;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.EquipmentRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.RentalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
@Controller
@RequiredArgsConstructor
public class InitialiseController {
    private final CertificationRepository certificationRepository;
    private final RentalRepository rentalRepository;
    private final DiverRepository diverRepository;
    private final EquipmentRepository equipmentRepository;

    @GetMapping("/initialise")
    private String initialiseDB() {
        if (!equipmentRepository.findAll().isEmpty()) {
            return "redirect:/";
        }

        ArrayList<Certification> certifications = new ArrayList<>();
        certifications.add(new Certification("None"));
        certifications.add(new Certification("OWD"));
        certifications.add(new Certification("AOWD"));
        certifications.add(new Certification("Nitrox"));
        certifications.add(new Certification("Instructor"));
        certificationRepository.saveAll(certifications);

        ArrayList<Equipment> equipment = new ArrayList<>();
        equipment.add(new Equipment("Scorpion 2", "BCD", "XL", "Cressi", certifications.get(1)));
        equipment.add(new Equipment("Scorpion 1", "BCD", "L", "Cressi", certifications.get(1)));
        equipment.add(new Equipment("M1", "Mask", "s", "Aqualung", certifications.get(0)));
        equipment.add(new Equipment("M2", "Mask", "L", "Aqualung", certifications.get(0)));
        equipment.add(new Equipment("Nitrox 21", "Tank", "12l", "Roth", certifications.get(3)));
        equipmentRepository.saveAll(equipment);


        ArrayList<Rental> rentals = new ArrayList<>();
        rentals.add(new Rental(equipment.get(0)));
        rentals.add(new Rental(equipment.get(1)));
        rentals.add(new Rental(equipment.get(2)));
        rentals.add(new Rental(equipment.get(2)));
        rentals.add(new Rental(equipment.get(3)));
        rentals.add(new Rental(equipment.get(3)));
        rentalRepository.saveAll(rentals);


        ArrayList<Diver> divers = new ArrayList<>();
        divers.add(new Diver("Max", "H", "Meier", certifications.get(0)));
        divers.add(new Diver("Ursula", "", "Meier", certifications.get(2)));
        divers.add(new Diver("Kurt", "", "Meier", certifications.get(2)));
        divers.add(new Diver("Marc", "", "Ledermann", certifications.get(4)));

        diverRepository.saveAll(divers);


        return "redirect:/";
    }


}
