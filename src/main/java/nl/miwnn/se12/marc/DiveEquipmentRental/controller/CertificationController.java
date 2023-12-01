package nl.miwnn.se12.marc.DiveEquipmentRental.controller;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.CertificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Handles everything for the certifications as RestController
 **/

@RestController
@RequestMapping("api/certification")
@RequiredArgsConstructor
public class CertificationController {
    private final CertificationService certificationService;

    @GetMapping("/all")
    public ResponseEntity<List<CertificationDto>> allCertifications() {
        return ResponseEntity.ok(certificationService.getAllCertifications());
    }

    @GetMapping("/get/{certificationId}")
    public ResponseEntity<CertificationDto> oneCertification(@PathVariable("certificationId") long certificationId) {
        return ResponseEntity.ok(certificationService.getCertification(certificationId));
    }

    @PostMapping("/new")
    public ResponseEntity<?> saveCertification(@RequestBody CertificationDto certificationDto) {
        certificationService.saveOrUpdateCertification(certificationDto);
        return ResponseEntity.ok("Certification added.");
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCertification(@RequestBody CertificationDto certificationDto) {
        certificationService.saveOrUpdateCertification(certificationDto);
        return ResponseEntity.ok("Certification added.");
    }

    @DeleteMapping("/delete/{certificationId}")
    public ResponseEntity<?> deleteCertification(@PathVariable("certificationId") long certificationId) {
        certificationService.deleteCertification(certificationId);
        return ResponseEntity.ok("Deleted Certification");
    }

}
