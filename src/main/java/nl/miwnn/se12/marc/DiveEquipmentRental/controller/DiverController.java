package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handles everthing abot the Diver.
 **/
@Controller
@RequestMapping("/diver")
@RequiredArgsConstructor
public class DiverController {
    private final DiverRepository diverRepository;
    private final CertificationRepository certificationRepository;

    @GetMapping("/all")
    protected String showDiverOverview(Model model) {
        model.addAttribute("allDivers", diverRepository.findAll());
        model.addAttribute("certifications", certificationRepository.findAll());
        return "diverOverview";
    }

    @GetMapping("/new")
    protected String showDiverForm(Model model) {
        model.addAttribute("diver", new Diver());
        model.addAttribute("certifications", certificationRepository.findAll());
        return "diverForm";
    }

    @GetMapping("/edit/{diverId}")
    protected String showDiverEditForm(@PathVariable("diverId") Long diverId, Model model) {
        Optional<Diver> diverOptional = diverRepository.findById(diverId);
        if (diverOptional.isEmpty()) {
            return "redirect:/diver/all";
        }
        model.addAttribute("diver", diverOptional.get());
        model.addAttribute("certifications", certificationRepository.findAll());
        return "diverForm";
    }

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

}
