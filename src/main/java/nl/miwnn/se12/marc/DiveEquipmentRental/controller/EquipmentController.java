package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handle all requests related to equipment.
 **/
@Controller
public class EquipmentController {
    @GetMapping("/")
    private String showEquipment() {
        return "equipmentOverview";
    }
}
