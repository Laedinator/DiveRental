package nl.miwnn.se12.marc.DiveEquipmentRental.service;

import nl.miwnn.se12.marc.DiveEquipmentRental.dto.DiverDto;

import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Defines the DiverService.
 **/
public interface DiverService {

    List<DiverDto> findAllDivers();

    DiverDto findDiverById(Long diverId);

    void saveOrUpdateDiver(DiverDto dto);

    void saveOrUpdateDiver(List<DiverDto> dtoList);

    void deleteDiver(Long diverId);
}
