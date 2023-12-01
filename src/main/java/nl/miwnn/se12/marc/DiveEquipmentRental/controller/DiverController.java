package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.RentalRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.DiverService;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handles everthing abot the Diver.
 **/
@RestController
@RequestMapping("/api/diver")
@RequiredArgsConstructor
public class DiverController {
    private final DiverRepository diverRepository;
    private final DiverService diverService;

    @GetMapping("/all")
    public ResponseEntity<List<DiverDto>> allDiverDtos() {
        return ResponseEntity.ok(diverService.findAllDivers());
    }

    @PutMapping("/update")
    public ResponseEntity<?> editDiver(@RequestBody DiverDto diverDto) {
        diverService.saveOrUpdateDiver(diverDto);
        return ResponseEntity.ok("updated diver.");
    }


    //TODO: Add Service Layer.
    @PostMapping("/new")
    protected String saveOrUpdateDiver(@ModelAttribute("diver") Diver diver, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            diverRepository.save(diver);
        }

        return "redirect:/diver/all";
    }

    @GetMapping("/delete/{diverId}")
    protected String deleteDiver(@PathVariable("diverId") Long diverId) {
        Optional<Diver> diverOptional = diverRepository.findById(diverId);
        if (diverOptional.isEmpty()) {
            return "redirect:/diver/all";
        }
        diverRepository.deleteById(diverId);
        return "redirect:/diver/all";
    }

    @GetMapping("/get/{diverId}")
    public ResponseEntity<DiverDto> diverDto(@PathVariable("diverId") long diverId) {
        return ResponseEntity.ok(diverService.findDiverById(diverId));
    }

}
