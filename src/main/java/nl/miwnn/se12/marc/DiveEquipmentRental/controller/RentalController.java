package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Rental;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.EquipmentRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.RentalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;


/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handle everything regarding rentals.
 **/

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    private final EquipmentRepository equipmentRepository;
    private final RentalRepository rentalRepository;

    @GetMapping({"/","/new"})
    private String showOverview() {
        return "redirect:/";
    }

    @GetMapping("/new/{equipmentId}")
    private String createNewRental(@PathVariable("equipmentId") Long equipmentId) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);

        if (equipmentOptional.isPresent()) {
            Rental rental = new Rental();
            rental.setEquipment(equipmentOptional.get());
            rentalRepository.save(rental);
        }

        return "redirect:/";
    }

    @GetMapping("/delete/{rentalId}")
    private String deleteRental(@PathVariable("rentalId") Long rentalId) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);

        if (rentalOptional.isEmpty()) {
            return "redirect:/";
        }

        rentalRepository.deleteById(rentalId);
        return String.format("redirect:/equipment/details/%s", rentalOptional.get().getEquipment().getName());
    }

    @GetMapping("/borrow/{rentalId}")
    private String makeRentalUnavailable(@PathVariable("rentalId") Long rentalId) {
        return getString(rentalId, false);
    }

    @GetMapping("/return/{rentalId}")
    private String makeRentalAvailable(@PathVariable("rentalId") Long rentalId) {
        return getString(rentalId, true);
    }

    private String getString(@PathVariable("rentalId") Long rentalId, boolean available) {
        Optional<Rental> rentalOptional = rentalRepository.findById(rentalId);

        if (rentalOptional.isEmpty()) {
            return "redirect:/";
        }

        Rental rental = rentalOptional.get();
        rental.setAvailable(available);
        rentalRepository.save(rental);

        return String.format("redirect:/equipment/details/%s", rental.getEquipment().getName());
    }

}

