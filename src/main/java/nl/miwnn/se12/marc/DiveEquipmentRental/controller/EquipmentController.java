package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Equipment;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.EquipmentRepository;
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
@RequiredArgsConstructor
public class EquipmentController {
    private final EquipmentRepository equipmentRepository;

    @GetMapping({"/", "./equipment/overview"})
    private String showEquipment(Model model) {
        model.addAttribute("allEquipment", equipmentRepository.findAll());

        return "equipmentOverview";
    }

    @GetMapping("/equipment/new")
    private String showEquipmentForm(Model model) {
        model.addAttribute("equipment", new Equipment());

        return "equipmentForm";
    }

    @GetMapping("/equipment/edit/{name}")
    private String showEquipmentEditForm(@PathVariable("name") String name, Model model) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByName(name);
        if (equipmentOptional.isEmpty()) {
            return "redirect:./equipment/overview";
        }
        model.addAttribute("equipment", equipmentOptional.get());

        return "equipmentForm";
    }

    @PostMapping("/equipment/new")
    private String saveOrUpdateEquipment(@ModelAttribute("equipment") Equipment equipmentToBeSaved,
                                         BindingResult result) {
        if (!result.hasErrors()) {
            equipmentRepository.save(equipmentToBeSaved);
        }

        return "redirect:/";
    }

    @GetMapping("/equipment/details/{name}")
    private String showEquipmentDetails(@PathVariable("name") String name, Model model) {
        Optional<Equipment> equipmentOptional = equipmentRepository.findEquipmentByName(name);
        if (equipmentOptional.isEmpty()) {
            return "redirect:./equipment/overview";
        }

        model.addAttribute("equipmentDetails", equipmentOptional.get());
        return "equipmentDetails";
    }

}
