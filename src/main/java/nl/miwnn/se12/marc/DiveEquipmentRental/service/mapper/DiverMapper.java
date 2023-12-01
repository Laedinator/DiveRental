package nl.miwnn.se12.marc.DiveEquipmentRental.service.mapper;

import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
@Component
public class DiverMapper {
    public DiverDto diverDto(Diver diver, List<CertificationDto> certificationDtoList) {
        return DiverDto.builder()
                .diverId(diver.getDiverId())
                .firstName(diver.getFirstName())
                .middleName(diver.getMiddleName())
                .lastName(diver.getLastName())
                .username(diver.getUsername())
                .certifications(certificationDtoList)
                .build();
    }

    public Diver diverModel(DiverDto dto, List<Certification> certifications) {
        Diver diver = new Diver();
        if (dto.getDiverId() != null) {
            diver.setDiverId(dto.getDiverId());
        }
        diver.setFirstName(dto.getFirstName());
        diver.setMiddleName(dto.getMiddleName());
        diver.setLastName(dto.getLastName());
        diver.setUsername(dto.getUsername());
        diver.setCertifications(certifications);

        return diver;
    }

    public Diver diverModel(Diver diver, DiverDto dto, List<Certification> certificationsFromDto) {

        diver.setFirstName(dto.getFirstName());
        diver.setMiddleName(dto.getMiddleName());
        diver.setLastName(dto.getLastName());
        diver.setUsername(dto.getUsername());
        diver.setCertifications(certificationsFromDto);

        return diver;
    }
}
