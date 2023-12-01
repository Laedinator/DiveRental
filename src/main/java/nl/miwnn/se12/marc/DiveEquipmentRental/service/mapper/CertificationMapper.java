package nl.miwnn.se12.marc.DiveEquipmentRental.service.mapper;

import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Maps the Certification and DTO.
 **/
@Component
public class CertificationMapper {

    public CertificationDto createDtoFromModel(Certification certification) {
        return CertificationDto.builder()
                .certificationId(certification.getCertificationId())
                .certificationName(certification.getName())
                .build();
    }

    public List<CertificationDto> createDtosFromModels(List<Certification> certifications) {
        List<CertificationDto> certificationDtos = new ArrayList<>();
        for (Certification certification : certifications) {
            certificationDtos.add(createDtoFromModel(certification));
        }
        return certificationDtos;
    }

    public List<Certification> createListFromDto(List<CertificationDto> certificationDtos) {
        List<Certification> certifications = new ArrayList<>();
        for (CertificationDto certification : certificationDtos) {
            certifications.add(createModelFromDto(certification));
        }
        return certifications;
    }


    public Certification createModelFromDto(CertificationDto dto) {
        Certification certification = new Certification();
        certification.setName(dto.getCertificationName());
        certification.setCertificationId(dto.getCertificationId());

        return certification;
    }
}
