package nl.miwnn.se12.marc.DiveEquipmentRental.service.implementation;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.CertificationDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Certification;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.CertificationRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.CertificationService;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.mapper.CertificationMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * handles Data regarding the Certification.
 **/
@RequiredArgsConstructor
@Service
public class CertificationServiceMySQL implements CertificationService {
    private final CertificationRepository certificationRepository;
    private final CertificationMapper certificationMapper;

    @Override
    public List<CertificationDto> getAllCertifications() {
        return certificationMapper.createDtosFromModels(certificationRepository.findAll());
    }

    @Override
    public List<CertificationDto> getCertificationsDtoForDiver(Diver diver) {
        return certificationMapper.createDtosFromModels(diver.getCertifications());
    }

    @Override
    public List<Certification> getCertificationsForDiver(DiverDto dto) {
        List<Certification> certificationList = new ArrayList<>();
        for (CertificationDto certification : dto.getCertifications()) {
            certificationList.add(certificationRepository.findById(certification.getCertificationId()).get());
        }
        return certificationList;
    }

    @Override
    public CertificationDto getCertification(long id) {
        return certificationMapper.createDtoFromModel(certificationRepository.getReferenceById(id));
    }

    @Override
    public void saveOrUpdateCertification(CertificationDto dto) {
        certificationRepository.save(certificationMapper.createModelFromDto(dto));
    }

    @Override
    public void saveOrUpdateCertification(List<CertificationDto> certificationDtos) {
        certificationRepository.saveAll(certificationMapper.createListFromDto(certificationDtos));
    }

    @Override
    public void deleteCertification(Long certificationId) {
        certificationRepository.deleteById(certificationId);
    }
}
