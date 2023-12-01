package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.EquipmentRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handle all requests related to equipment.
 **/
@Controller
@RequestMapping({"/", "/equipment"})
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentRepository equipmentRepository;
    private final CertificationRepository certificationRepository;


    @GetMapping({"/", "/overview"})
    private String showEquipment(@AuthenticationPrincipal Diver diver, Model model) {
        model.addAttribute("allEquipment", equipmentRepository.findAll());
        model.addAttribute("certifications", certificationRepository.findAll());

        return "equipmentOverview";
    }

    @GetMapping("/new")
    private String showEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());
        model.addAttribute("certifications", certificationRepository.findAll());

        return "equipmentForm";
    }

    @GetMapping("/edit/{name}")
    private String showEquipmentEditForm(@PathVariable("name") String name, Model model) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByName(name);
        if (equipmentOptional.isEmpty()) {
            return "redirect:/equipment/";
        }
        model.addAttribute("equipment", equipmentOptional.get());
        model.addAttribute("certifications", certificationRepository.findAll());

        return "equipmentForm";
    }

    @PostMapping("/new")
    private String saveOrUpdateEquipment(@ModelAttribute("equipment") Equipment equipmentToBeSaved,
                                         BindingResult result) {
        if (!result.hasErrors()) {
            equipmentRepository.save(equipmentToBeSaved);
        }

        return "redirect:/overview/";
    }

    @GetMapping("details/{name}")
    private String showEquipmentDetails(@PathVariable("name") String name, Model model) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByName(name);
        if (equipmentOptional.isEmpty()) {
            return "redirect:./equipment/overview";
        }
        model.addAttribute("equipment", equipmentOptional.get());
        model.addAttribute("certifications", certificationRepository.findAll());
        return "/equipmentDetails";
    }

    @GetMapping("/delete/{equipmentId}")
    private String deleteEquipment(@PathVariable("equipmentId") Long equipmentId) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findById(equipmentId);
        if (equipmentOptional.isEmpty()) {
            return "redirect:/";
        }

        equipmentRepository.deleteById(equipmentId);
        return "redirect:/";
    }

}
