package nl.miwnn.se12.marc.DiveEquipmentRental.service;

import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;

import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * defines the functions of a certification service.
 **/
public interface CertificationService {
    List<CertificationDto> getAllCertifications();

    List<CertificationDto> getCertificationsDtoForDiver(Diver diver);

    List<Certification> getCertificationsForDiver(DiverDto dto);

    CertificationDto getCertification(long certificationId);

    void saveOrUpdateCertification(CertificationDto certificationDto);

    void saveOrUpdateCertification(List<CertificationDto> certificationDtos);

    void deleteCertification(Long certificationId);
}