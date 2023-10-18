package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.ManyToOne;


/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handles everything for the certifications
 **/
@Getter
@Setter
@Controller
@RequestMapping("/certification")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationRepository certificationRepository;

    @ManyToOne
    private Diver diver;

    @GetMapping("/certification")
    protected String showDiverOverview(Model model) {
        model.addAttribute("certifications", certificationRepository.findAll());
        return "diverOverview";
    }
}
