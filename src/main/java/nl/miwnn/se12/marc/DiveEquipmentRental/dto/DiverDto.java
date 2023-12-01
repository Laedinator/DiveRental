package nl.miwnn.se12.marc.DiveEquipmentRental.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Marc Ledermann
 * <m.ledermann@st.hanze.nl>
 * Purpose of the program:
 * Defines the DTO of the diver.
 **/
@Getter
@Setter
@Builder
public class DiverDto {
    private Long diverId;

    private String firstName;
    private String middleName;
    private String lastName;
    private String username;
    private String password;

    private List<CertificationDto> certifications = new ArrayList<>();

    public void addCertification(CertificationDto dto) {
        certifications.add(dto);
    }
}
