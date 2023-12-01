package nl.miwnn.se12.marc.DiveEquipmentRental.service.implementation;

import lombok.RequiredArgsConstructor;
import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;
import nl.miwnn.se12.marc.DiveEquipmentRental.model.Diver;
import nl.miwnn.se12.marc.DiveEquipmentRental.repository.DiverRepository;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.CertificationService;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.DiverService;
import nl.miwnn.se12.marc.DiveEquipmentRental.service.mapper.DiverMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 **/
@RequiredArgsConstructor
@Service
public class DiverServiceMySQL implements DiverService {
    private final DiverRepository diverRepository;
    private final DiverMapper diverMapper;
    private final CertificationService certificationService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public List<DiverDto> findAllDivers() {
        List<Diver> diverOptional = diverRepository.findAll();
        if (diverOptional.isEmpty()) {
            return null;
        }
        List<DiverDto> diverDtos = new ArrayList<>();
        for (Diver diver : diverOptional) {
            diverDtos.add(diverMapper.diverDto(diver, certificationService.getCertificationsDtoForDiver(diver)));
        }

        return diverDtos;
    }

    @Override
    public DiverDto findDiverById(Long diverId) {
        Optional<Diver> diverOptional = diverRepository.findById(diverId);
        return diverOptional.map(diver -> diverMapper.diverDto(diver, certificationService.getCertificationsDtoForDiver(diver)))
                .orElse(null);
    }

    @Override
    public void saveOrUpdateDiver(DiverDto dto) {
        Optional<Diver> diverOptional = diverRepository.findById(dto.getDiverId());
        if (diverOptional.isPresent()) {
            diverRepository.save(diverMapper.diverModel(
                    diverOptional.get(), dto, certificationService.getCertificationsForDiver(dto)));
        } else {
            Diver diverToSave = diverMapper.diverModel(dto, certificationService.getCertificationsForDiver(dto));
            diverToSave.setPassword(passwordEncoder.encode(dto.getPassword()));
            diverRepository.save(diverToSave);
        }
    }

    @Override
    public void saveOrUpdateDiver(List<DiverDto> dtoList) {

    }

    @Override
    public void deleteDiver(Long diverId) {

    }
}
